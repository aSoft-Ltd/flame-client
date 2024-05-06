@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.transformers.documents.toAttachment
import kollections.forEach
import koncurrent.Later
import koncurrent.later.then
import kotlinx.JsExport

abstract class OwnSmeAbstractDocumentScene(
    private val options: SmeSceneOption<OwnSmeScheme>,
) : SmeAbstractDocumentScene(options) {

    fun initialize(): Later<Any> = options.api.load().then { sme ->
        documents.forEach {
            it.initialize(
                attachment = it.options.document.toAttachment(sme),
                onSuccess = {
                    options.bus.dispatch(options.topic.progressMade())
                    deInitialize()
                    initialize()
                }
            )
        }
    }
}
