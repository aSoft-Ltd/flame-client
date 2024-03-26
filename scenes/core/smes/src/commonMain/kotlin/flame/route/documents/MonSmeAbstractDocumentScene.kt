@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.route.documents

import flame.MonSmeScheme
import flame.SmeSceneOption
import flame.transformers.documents.toAttachment
import kollections.forEach
import koncurrent.later.then
import kotlinx.JsExport

abstract class MonSmeAbstractDocumentScene(
    private val options: SmeSceneOption<MonSmeScheme>
) : SmeAbstractDocumentScene(options) {
    fun initialize(uid: String) {
        options.api.load(uid).then { sme ->
            documents.forEach {
                it.initialize(
                    attachment = it.options.document.toAttachment(sme),
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
