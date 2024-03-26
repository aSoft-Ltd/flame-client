@file:JsExport

package flame.route.ratios.pnl.output

import kotlinx.JsExport

class SmeAnnualEBITDA(
    var interestIncome: Double? = null,
    var finances: Double? = null,
    var depreciation: Double? = null
) {
    val total get() = listOf(interestIncome, finances, depreciation).sumOf { it ?: 0.0 }
}