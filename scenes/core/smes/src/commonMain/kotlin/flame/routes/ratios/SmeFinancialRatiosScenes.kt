@file:JsExport

package flame.routes.ratios

import flame.routes.ratios.pnl.SmePnLScene
import flame.routes.ratios.pnl.SpreadSheets
import kotlinx.JsExport

sealed interface SmeFinancialRatiosScenes {
    val pnl: SmePnLScene
    val sheets: SpreadSheets
}