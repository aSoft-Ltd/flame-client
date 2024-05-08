@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.transformers.documents.toAttachment
import kollections.forEach
import koncurrent.Later
import koncurrent.later.zip
import kotlinx.JsExport

abstract class OwnSmeAbstractDocumentScene(
    private val options: SmeSceneOptions<OwnSmeScheme>,
) : SmeAbstractDocumentScene(options) {

    fun initialize(): Later<Any> = options.api.load().zip(options.auth.session()) { (sme,session) ->
        documents.forEach {
            it.initialize(
                attachment = it.options.document.toAttachment(sme,options.toAttachmentOptions(session)),
                onSuccess = {
                    options.bus.dispatch(options.topic.progressMade())
                    deInitialize()
                    initialize()
                }
            )
        }
    }
}
