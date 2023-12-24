@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import cinematic.BaseScene
import flame.SmeApi
import flame.SmeSceneOption
import flame.documents.SmeDocument
import flame.routes.documents.utils.SmeDocumentScene
import flame.routes.documents.utils.SmeDocumentSceneOptions
import flame.transformers.documents.toAttachment
import kollections.List
import kollections.forEach
import koncurrent.later.then
import kotlinx.JsExport
import kotlinx.JsExportIgnore

abstract class SmeAbstractDocumentScene(private val options: SmeSceneOption<SmeApi>) : BaseScene() {

    abstract val documents : List<SmeDocumentScene>

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

    fun deInitialize() = documents.forEach { it.deInitialize() }

    @JsExportIgnore
    protected fun SmeDocument.toScene() = SmeDocumentScene(SmeDocumentSceneOptions(this, options.wm))
}
