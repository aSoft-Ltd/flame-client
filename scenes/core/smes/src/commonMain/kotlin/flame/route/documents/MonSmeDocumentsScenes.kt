@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.route.documents

import flame.MonSmeScheme
import flame.OwnSmeScheme
import flame.SmeSceneOption
import kotlinx.JsExport

class MonSmeDocumentsScenes(options: SmeSceneOption<MonSmeScheme>) : SmeDocumentsScenes {
    override val list by lazy { MonSmeDocumentListScene(options) }
    override val financials by lazy { MonSmeFinancialRecordsScene(options) }
    override val additional by lazy { MonSmeAdditionalDocumentsScene(options) }
}