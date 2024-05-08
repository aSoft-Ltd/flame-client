@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.MonSmeScheme
import flame.SmeSceneOptions
import kotlinx.JsExport

class MonSmeDocumentsScenes(options: SmeSceneOptions<MonSmeScheme>) : SmeDocumentsScenes {
    override val list by lazy { MonSmeDocumentListScene(options) }
    override val financials by lazy { MonSmeFinancialRecordsScene(options) }
    override val additional by lazy { MonSmeAdditionalDocumentsScene(options) }
}