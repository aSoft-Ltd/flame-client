@file:JsExport

package flame

import flame.route.admin.SmeAdminScenes
import flame.route.documents.SmeDocumentsScenes
import flame.route.financial.SmeFinancialScenes
import flame.route.governance.SmeGovernanceFormScene
import flame.route.info.SmeInfoScene
import flame.route.ratios.SmeFinancialRatiosScenes
import flame.route.swot.SmeSwotTheater
import kotlinx.JsExport

interface XSmeScenes {
    val admin: SmeAdminScenes
    val info: SmeInfoScene
    val documents: SmeDocumentsScenes
    val finance: SmeFinancialScenes
    val governance: SmeGovernanceFormScene
    val swot: SmeSwotTheater
    val ratios: SmeFinancialRatiosScenes
}