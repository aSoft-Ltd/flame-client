@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.documents

import kotlin.js.JsExport

data class SmeDocumentsPresenter(
    val list: SmeDocumentListPresenter? = null,
    val financials: SmeFinancialRecordsPresenter? = null,
    val additional: SmeMiscellaneousDocumentsPresenter? = null
)