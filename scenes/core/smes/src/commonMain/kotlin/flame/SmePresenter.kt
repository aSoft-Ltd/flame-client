@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import cabinet.AttachmentDto
import cabinet.AttachmentPresenter
import flame.admin.SmeAdminPresenter
import flame.admin.SmeBusinessDto
import flame.analysis.FinancialReportsDto
import flame.documents.SmeDocumentsPresenter
import flame.finance.SmeFinanceDto
import flame.funding.SmeFundingDto
import flame.governance.SmeGovernanceDto
import flame.plan.SmePlanDto
import flame.sheet.SmeSheet
import flame.swot.SmeSwotDto
import kollections.List
import kotlinx.JsExport

data class SmePresenter(
    val src: SmeDto,
    val uid: String,
    val company: String,
    val admin: SmeAdminPresenter?,
    val funding: SmeFundingDto?,
    val finance: SmeFinanceDto?,
    val documents: SmeDocumentsPresenter?,
    val xlsx: AttachmentPresenter? = null,
    val governance: SmeGovernanceDto?,
    val plan: SmePlanDto,
    val swot: SmeSwotDto?,
    val reports: List<FinancialReportsDto>,
    val sheet: SmeSheet?
)