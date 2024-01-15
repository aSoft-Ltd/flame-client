@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.SmeApi
import flame.SmeSceneOption
import flame.documents.SmeDocument
import kollections.toList
import kotlinx.JsExport

class SmeAdditionalDocumentsScene(options: SmeSceneOption<SmeApi>) : SmeAbstractDocumentScene(options) {

    override val documents by lazy {
        SmeDocument.Additional.values().map { it.toScene() }.toList()
    }
}
