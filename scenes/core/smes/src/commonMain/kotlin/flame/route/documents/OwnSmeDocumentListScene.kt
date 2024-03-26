@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.route.documents

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.documents.SmeDocument
import kollections.toList
import kotlinx.JsExport

class OwnSmeDocumentListScene(options: SmeSceneOption<OwnSmeScheme>) : OwnSmeAbstractDocumentScene(options) {
    override val documents by lazy {
        SmeDocument.List.values().map { it.toScene() }.toList()
    }
}
