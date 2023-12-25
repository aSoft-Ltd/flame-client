@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.documents

import cabinet.Attachment
import kotlinx.JsExport

data class SmeFinancialRecordsPresenter(
    val balanceSheet: Attachment? = null,
    val incomeStatement: Attachment? = null,
    val cashFlow: Attachment? = null,
    val assetRegister: Attachment? = null,
    val financialProjections: Attachment? = null,
    val salesPipeline: Attachment? = null,
)
