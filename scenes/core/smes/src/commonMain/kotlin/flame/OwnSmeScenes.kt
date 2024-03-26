@file:JsExport

package flame

import flame.route.admin.OwnSmeAdminScenes
import flame.route.documents.OwnSmeDocumentsScenes
import flame.route.documents.SmeDocumentsScenes
import flame.route.financial.OwnSmeFinancialScenes
import flame.route.financial.SmeFinancialScenes
import flame.route.governance.OwnSmeGovernanceFormScene
import flame.route.governance.SmeGovernanceFormScene
import flame.route.info.OwnSmeInfoScene
import flame.route.ratios.OwnSmeFinancialRatios
import flame.route.swot.OwnSmeSwotTheater
import kotlinx.JsExport

class OwnSmeScenes(options: SmeSceneOption<OwnSmeScheme>) : XSmeScenes {
    override val info by lazy { OwnSmeInfoScene(options) }
    override val admin by lazy { OwnSmeAdminScenes(options) }
    override val documents by lazy { OwnSmeDocumentsScenes(options) }
    override val finance by lazy { OwnSmeFinancialScenes(options) }
    override val governance by lazy { OwnSmeGovernanceFormScene(options) }
    override val swot by lazy { OwnSmeSwotTheater(options) }
    override val ratios by lazy { OwnSmeFinancialRatios(options) }
}