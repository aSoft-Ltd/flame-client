@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.documents.SmeDocument
import kollections.toList
import kotlinx.JsExport

class MonSmeDocumentListScene(options: SmeSceneOptions<MonSmeScheme>) : MonSmeAbstractDocumentScene(options) {
    override val documents by lazy {
        SmeDocument.List.values().map { it.toScene() }.toList()
    }
}
