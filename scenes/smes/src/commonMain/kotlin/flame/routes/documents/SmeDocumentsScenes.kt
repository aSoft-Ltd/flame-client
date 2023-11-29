@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.SmeApi
import flame.SmeSceneOption
import kotlin.js.JsExport

class SmeDocumentsScenes(private val options: SmeSceneOption<SmeApi>) {
    val list by lazy { SmeDocumentListScene(options) }
    val financials by lazy { SmeFinancialRecordsScene(options) }
    val additional by lazy { SmeAdditionalDocumentsScene(options) }
}