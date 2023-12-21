@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import flame.admin.SmeAdminPresenter
import flame.documents.SmeDocumentsPresenter
import flame.finance.SmeFinanceDto
import flame.funding.SmeFundingDto
import flame.swot.SmeSwotDto
import kotlinx.JsExport

data class SmePresenter(
    val src: SmeDto,
    val uid: String,
    val company: String,
    val admin: SmeAdminPresenter?,
    val funding: SmeFundingDto?,
    val finance: SmeFinanceDto?,
    val documents: SmeDocumentsPresenter?,
    val swot: SmeSwotDto?
)