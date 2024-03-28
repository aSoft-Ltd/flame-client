@file:JsExport

package flame

import flame.routes.admin.SmeAdminScenes
import flame.routes.documents.SmeDocumentsScenes
import flame.routes.financial.SmeFinancialScenes
import flame.routes.governance.SmeGovernanceFormScene
import flame.routes.info.SmeInfoScene
import flame.routes.ratios.SmeFinancialRatiosScenes
import flame.routes.swot.SmeSwotTheater
import kotlinx.JsExport

sealed interface SmeScenes {
    val admin: SmeAdminScenes
    val info: SmeInfoScene
    val documents: SmeDocumentsScenes
    val finance: SmeFinancialScenes
    val governance: SmeGovernanceFormScene
    val swot: SmeSwotTheater
    val ratios: SmeFinancialRatiosScenes
}