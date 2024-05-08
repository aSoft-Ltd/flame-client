@file:JsExport

package flame.routes.ratios

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.routes.ratios.pnl.MonSmePnLScene
import flame.routes.ratios.pnl.SpreadSheets
import kotlinx.JsExport

class MonSmeFinancialRatiosScenes(
    private val option: SmeSceneOptions<MonSmeScheme>
) : SmeFinancialRatiosScenes {
    override val pnl by lazy { MonSmePnLScene(option) }
    override val sheets by lazy { SpreadSheets(option) }
}