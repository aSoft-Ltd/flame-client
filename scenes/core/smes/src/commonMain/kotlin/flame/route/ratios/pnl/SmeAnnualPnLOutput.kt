@file:JsExport
package flame.route.ratios.pnl

import flame.route.ratios.pnl.output.SmeAnnualEBITDA
import flame.route.ratios.pnl.output.SmeAnnualExpenses
import flame.route.ratios.pnl.output.SmeAnnualProfit
import flame.route.ratios.pnl.output.SmeAnnualRevenue
import flame.route.ratios.pnl.output.SmeAnnualTax
import kotlinx.JsExport

class SmeAnnualPnLOutput(
    val revenue: SmeAnnualRevenue = SmeAnnualRevenue(),
    val expenses: SmeAnnualExpenses = SmeAnnualExpenses(),
    val ebitda: SmeAnnualEBITDA = SmeAnnualEBITDA(),
    val tax: SmeAnnualTax = SmeAnnualTax()
) {

    val profit
        get() = run {
            val rev = revenue.total
            val bt = rev - expenses.total - ebitda.total
            SmeAnnualProfit(
                gross = rev,
                beforeTax = bt,
                net = bt - tax.total
            )
        }
}