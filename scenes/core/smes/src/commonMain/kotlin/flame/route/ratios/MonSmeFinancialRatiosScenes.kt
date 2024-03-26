@file:JsExport

package flame.route.ratios

import flame.MonSmeScheme
import flame.SmeApi
import flame.SmeSceneOption
import flame.route.ratios.pnl.MonSmePnLScene
import flame.route.ratios.pnl.SmePnLScene
import flame.route.ratios.pnl.SpreadSheets
import kotlinx.JsExport

class MonSmeFinancialRatiosScenes(
    private val option: SmeSceneOption<MonSmeScheme>
) : SmeFinancialRatiosScenes {
    override val pnl by lazy { MonSmePnLScene(option) }
    override val sheets by lazy { SpreadSheets(option) }
}