@file:JsExport

package flame

import flame.routes.admin.OwnSmeAdminScenes
import flame.routes.documents.OwnSmeDocumentsScenes
import flame.routes.financial.OwnSmeFinancialScenes
import flame.routes.financial.analysis.FinancialAnalysisScene
import flame.routes.financial.analysis.FinancialAnalysisScenes
import flame.routes.financial.analysis.OwnFinancialAnalysisScene
import flame.routes.governance.OwnSmeGovernanceTheater
import flame.routes.info.OwnSmeInfoScene
import flame.routes.plan.OwnSmeBusinessPlanTheater
import flame.routes.ratios.OwnSmeFinancialRatios
import flame.routes.swot.OwnSmeSwotTheater
import kotlinx.JsExport

class OwnSmeScenes(options: SmeSceneOptions<OwnSmeApi>) : SmeScenes {
    override val info by lazy { OwnSmeInfoScene(options) }
    override val admin by lazy { OwnSmeAdminScenes(options) }
    override val documents by lazy { OwnSmeDocumentsScenes(options) }
    override val finance by lazy { OwnSmeFinancialScenes(options) }
    override val governance by lazy { OwnSmeGovernanceTheater(options) }
    override val plan by lazy { OwnSmeBusinessPlanTheater(options) }
    override val swot by lazy { OwnSmeSwotTheater(options) }
    override val ratios by lazy { OwnSmeFinancialRatios(options) }
    override val analysis by lazy { FinancialAnalysisScenes() }
    override val reports by lazy { OwnFinancialAnalysisScene(options) }
}