@file:JsExport

package flame.routes.ratios

import flame.SmeApi
import flame.SmeSceneOption
import flame.routes.ratios.pnl.SmePnLScene
import flame.routes.ratios.pnl.SpreadSheets
import kotlinx.JsExport

class SmeFinancialRatios(private val option: SmeSceneOption<SmeApi>) {
    val pnl by lazy { SmePnLScene(option) }
    val sheets by lazy { SpreadSheets(option) }
}