@file:JsExport

package flame

import flame.routes.admin.MonSmeAdminScenes
import flame.routes.documents.MonSmeDocumentsScenes
import flame.routes.financial.MonSmeFinancialScenes
import flame.routes.governance.MonSmeGovernanceTheater
import flame.routes.info.MonSmeInfoScene
import flame.routes.plan.MonSmeBusinessPlanTheater
import flame.routes.ratios.MonSmeFinancialRatiosScenes
import flame.routes.swot.MonSmeSwotTheater
import kotlinx.JsExport

class MonSmeScenes(options: SmeSceneOptions<MonSmeScheme>) : SmeScenes {
    override val info by lazy { MonSmeInfoScene(options) }
    override val admin by lazy { MonSmeAdminScenes(options) }
    override val documents by lazy { MonSmeDocumentsScenes(options) }
    override val finance by lazy { MonSmeFinancialScenes(options) }
    override val governance by lazy { MonSmeGovernanceTheater(options) }
    override val plan by lazy { MonSmeBusinessPlanTheater(options) }
    override val swot by lazy { MonSmeSwotTheater(options) }
    override val ratios by lazy { MonSmeFinancialRatiosScenes(options) }
}