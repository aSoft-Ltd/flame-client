@file:JsExport

package flame.routes.ratios

import flame.MonSmeScheme
import flame.SmeSceneOption
import flame.routes.ratios.pnl.MonSmePnLScene
import flame.routes.ratios.pnl.SpreadSheets
import kotlinx.JsExport

class MonSmeFinancialRatiosScenes(
    private val option: SmeSceneOption<MonSmeScheme>
) : SmeFinancialRatiosScenes {
    override val pnl by lazy { MonSmePnLScene(option) }
    override val sheets by lazy { SpreadSheets(option) }
}