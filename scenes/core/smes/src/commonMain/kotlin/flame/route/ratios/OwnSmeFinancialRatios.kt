@file:JsExport

package flame.route.ratios

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.route.ratios.pnl.OwnSmePnLScene
import flame.route.ratios.pnl.SpreadSheets
import kotlinx.JsExport

class OwnSmeFinancialRatios(private val option: SmeSceneOption<OwnSmeScheme>) : SmeFinancialRatiosScenes {
    override val pnl by lazy { OwnSmePnLScene(option) }
    override val sheets by lazy { SpreadSheets(option) }
}