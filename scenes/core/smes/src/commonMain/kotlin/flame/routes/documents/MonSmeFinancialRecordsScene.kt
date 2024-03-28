@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.MonSmeScheme
import flame.SmeSceneOption
import flame.documents.SmeDocument
import kollections.toList
import kotlinx.JsExport

class MonSmeFinancialRecordsScene(options: SmeSceneOption<MonSmeScheme>) : MonSmeAbstractDocumentScene(options) {

    override val documents by lazy {
        SmeDocument.FinancialRecord.values().map { it.toScene() }.toList()
    }
}
