@file:JsExport

package flame.routes.ratios

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.routes.ratios.pnl.OwnSmePnLScene
import flame.routes.ratios.pnl.SpreadSheets
import kotlinx.JsExport

class OwnSmeFinancialRatios(private val option: SmeSceneOptions<OwnSmeScheme>) : SmeFinancialRatiosScenes {
    override val pnl by lazy { OwnSmePnLScene(option) }
    override val sheets by lazy { SpreadSheets(option) }
}