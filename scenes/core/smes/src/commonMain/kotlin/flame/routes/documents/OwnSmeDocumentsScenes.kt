@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.OwnSmeScheme
import flame.SmeSceneOption
import kotlinx.JsExport

class OwnSmeDocumentsScenes(options: SmeSceneOption<OwnSmeScheme>) : SmeDocumentsScenes {
    override val list by lazy { OwnSmeDocumentListScene(options) }
    override val financials by lazy { OwnSmeFinancialRecordsScene(options) }
    override val additional by lazy { OwnSmeAdditionalDocumentsScene(options) }
}