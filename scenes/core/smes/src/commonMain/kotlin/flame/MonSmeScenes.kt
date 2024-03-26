@file:JsExport

package flame

import flame.route.admin.MonSmeAdminScenes
import flame.route.documents.MonSmeDocumentsScenes
import flame.route.financial.MonSmeFinancialScenes
import flame.route.governance.MonSmeGovernanceFormScene
import flame.route.info.MonSmeInfoScene
import flame.route.ratios.MonSmeFinancialRatiosScenes
import flame.route.swot.MonSmeSwotTheater
import kotlinx.JsExport

class MonSmeScenes(options: SmeSceneOption<MonSmeScheme>) : XSmeScenes {
    override val info by lazy { MonSmeInfoScene(options) }
    override val admin by lazy { MonSmeAdminScenes(options) }
    override val documents by lazy { MonSmeDocumentsScenes(options) }
    override val finance by lazy { MonSmeFinancialScenes(options) }
    override val governance by lazy { MonSmeGovernanceFormScene(options) }
    override val swot by lazy { MonSmeSwotTheater(options) }
    override val ratios by lazy { MonSmeFinancialRatiosScenes(options) }
}