@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.documents

import cabinet.AttachmentPresenter
import kotlinx.JsExport

data class SmeFinancialRecordsPresenter(
//    val balanceSheet: AttachmentPresenter? = null,
//    val incomeStatement: AttachmentPresenter? = null,
//    val cashFlow: AttachmentPresenter? = null,
    val currentYearFinancials: AttachmentPresenter? = null,
    val priorYearFinancialStatementYear1: AttachmentPresenter? = null,
    val priorYearFinancialStatementYear2: AttachmentPresenter? = null,
    val assetRegister: AttachmentPresenter? = null,
    val financialProjections: AttachmentPresenter? = null,
    val salesPipeline: AttachmentPresenter? = null,
)
