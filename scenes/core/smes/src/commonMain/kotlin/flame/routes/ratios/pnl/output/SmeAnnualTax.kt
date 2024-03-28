@file:JsExport
package flame.routes.ratios.pnl.output

import kotlinx.JsExport

class SmeAnnualTax(var income: Double? = null) {
    val total get() = income ?: 0.0
}