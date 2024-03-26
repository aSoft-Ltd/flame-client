@file:JsExport
package flame.route.ratios.pnl.output

import kotlinx.JsExport

class SmeAnnualTax(var income: Double? = null) {
    val total get() = income ?: 0.0
}