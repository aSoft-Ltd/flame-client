@file:JsExport

package flame.routes.ratios.pnl.output

import kotlinx.JsExport

class SmeAnnualRevenue(
    var cos: Double? = null
) {
    val total get() = cos ?: 0.0
}