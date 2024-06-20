@file:JsExport

package flame

import flame.routes.admin.SmeAdminScenes
import flame.routes.documents.SmeDocumentsScenes
import flame.routes.financial.SmeFinancialScenes
import flame.routes.financial.analysis.FinancialAnalysisScenes
import flame.routes.governance.SmeGovernanceTheater
import flame.routes.governance.SmeManPowerFormScene
import flame.routes.info.SmeInfoScene
import flame.routes.plan.SmeBusinessPlanTheater
import flame.routes.ratios.SmeFinancialRatiosScenes
import flame.routes.swot.SmeSwotTheater
import kotlinx.JsExport

sealed interface SmeScenes {
    val admin: SmeAdminScenes
    val info: SmeInfoScene
    val documents: SmeDocumentsScenes
    val finance: SmeFinancialScenes
    val governance: SmeGovernanceTheater
    val plan: SmeBusinessPlanTheater
    val swot: SmeSwotTheater
    val ratios: SmeFinancialRatiosScenes
    val analysis: FinancialAnalysisScenes
}