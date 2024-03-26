@file:JsExport
package flame.route.ratios.pnl.output

import kotlinx.JsExport

class SmeAnnualExpenses(
    var administrative: Double? = null,
    var salaries: Double? = null,
    var other: Double? = null
) {
    val total get() = listOf(administrative, salaries, other).sumOf { it ?: 0.0 }
}