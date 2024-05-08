@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import kotlinx.JsExport

class OwnSmeDocumentsScenes(options: SmeSceneOptions<OwnSmeScheme>) : SmeDocumentsScenes {
    override val list by lazy { OwnSmeDocumentListScene(options) }
    override val financials by lazy { OwnSmeFinancialRecordsScene(options) }
    override val additional by lazy { OwnSmeAdditionalDocumentsScene(options) }
}