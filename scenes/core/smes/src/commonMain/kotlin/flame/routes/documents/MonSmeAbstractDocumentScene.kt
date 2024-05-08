@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.transformers.documents.toAttachment
import kollections.forEach
import koncurrent.later.zip
import kotlinx.JsExport

abstract class MonSmeAbstractDocumentScene(
    private val options: SmeSceneOptions<MonSmeScheme>
) : SmeAbstractDocumentScene(options) {
    fun initialize(uid: String) {
        options.api.load(uid).zip(options.auth.session()) { (sme, session) ->
            documents.forEach {
                it.initialize(
                    attachment = it.options.document.toAttachment(sme, options.toAttachmentOptions(session)),
                    onSuccess = {
                        options.bus.dispatch(options.topic.progressMade())
                        deInitialize()
                        initialize(uid)
                    }
                )
            }
        }
    }
}
