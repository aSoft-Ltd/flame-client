@file:JsExport

package flame.route.ratios.pnl

import kotlinx.JsExport
import symphony.Fields
import symphony.double

class SmeAnnualPnLFields(output: SmeAnnualPnLOutput) : Fields<SmeAnnualPnLOutput>(output) {

    val costOfSales = double(
        name = output.revenue::cos,
        label = "Cost of Sales",
        onChange = { notify() }
    )

    val administrativeExpenses = double(
        name = output.expenses::administrative,
        label = "Administrative Expenses",
        onChange = { notify() }
    )

    val otherOperatingExpenses = double(
        name = output.expenses::other,
        label = "Other Operating Expenses",
        onChange = { notify() }
    )

    val salariesAndStaffCost = double(
        name = output.expenses::salaries,
        label = "Salaries & Staff Cost",
        onChange = { notify() }
    )

    val interestIncome = double(
        name = output.ebitda::interestIncome,
        label = "Interest Income",
        onChange = { notify() }
    )

    val financesCost = double(
        name = output.ebitda::finances,
        label = "Finances Cost",
        onChange = { notify() }
    )

    val depreciation = double(
        name = output.ebitda::depreciation,
        label = "Depreciation & Amortisations",
        onChange = { notify() }
    )

    val incomeTaxExpense = double(
        name = output.tax::income,
        label = "Income tax expenses",
        onChange = { notify() }
    )
}