@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import cinematic.BaseScene
import flame.SmeSceneOption
import flame.XSmeScheme
import flame.documents.SmeDocument
import flame.routes.documents.utils.SmeDocumentScene
import flame.routes.documents.utils.SmeDocumentSceneOptions
import kollections.List
import kollections.forEach
import kotlinx.JsExport
import kotlinx.JsExportIgnore

abstract class SmeAbstractDocumentScene(private val options: SmeSceneOption<XSmeScheme>) : BaseScene() {

    abstract val documents : List<SmeDocumentScene>

    fun deInitialize() = documents.forEach { it.deInitialize() }

    @JsExportIgnore
    protected fun SmeDocument.toScene() = SmeDocumentScene(SmeDocumentSceneOptions(this, options.wm))
}