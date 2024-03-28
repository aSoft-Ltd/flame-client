@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.documents.SmeDocument
import kollections.toList
import kotlinx.JsExport

class OwnSmeAdditionalDocumentsScene(options: SmeSceneOption<OwnSmeScheme>) : OwnSmeAbstractDocumentScene(options) {

    override val documents by lazy {
        SmeDocument.Additional.values().map { it.toScene() }.toList()
    }
}
