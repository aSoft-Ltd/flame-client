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

data class SmeInfoPresenter(
    val sme: SmePresenter,
    val progress: SmeProgress
)