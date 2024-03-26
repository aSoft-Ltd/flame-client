@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.route.documents

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.documents.SmeDocument
import flame.route.documents.utils.SmeDocumentScene
import flame.route.documents.utils.SmeDocumentSceneOptions
import flame.transformers.documents.toAttachment
import kollections.List
import kollections.forEach
import koncurrent.later.then
import kotlinx.JsExport
import kotlinx.JsExportIgnore

abstract class OwnSmeAbstractDocumentScene(
    private val options: SmeSceneOption<OwnSmeScheme>,
) : SmeAbstractDocumentScene(options) {

    fun initialize() {
        options.api.load().then { sme ->
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
}
