@file:JsExport

package flame.route.ratios

import flame.route.ratios.pnl.SmePnLScene
import flame.route.ratios.pnl.SpreadSheets
import kotlinx.JsExport

sealed interface SmeFinancialRatiosScenes {
    val pnl:SmePnLScene
    val sheets: SpreadSheets
}