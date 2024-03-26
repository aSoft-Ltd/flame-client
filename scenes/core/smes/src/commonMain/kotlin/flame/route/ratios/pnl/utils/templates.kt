package flame.route.ratios.pnl.utils

import flame.route.ratios.utils.SmeEntryField
import kollections.listOf

internal fun defaultTemplate() = listOf(revenue(), expenses(), ebitda(), beforeTax(), tax(), verdict())

private fun revenue() = SmeEntryField(
    name = "Revenue",
    children = listOf(
        SmeEntryField(
            name = "Cost of Sales",
            isAggregate = false
        ),
        SmeEntryField(
            name = "Gross profit",
            isAggregate = true
        )
    )
)

private fun expenses() = SmeEntryField(
    name = "Expenses",
    children = listOf("Administrative Expenses", "Other Operating Expenses", "Salary & Staff Cost")
)

private fun ebitda() = SmeEntryField(
    name = "EBITDA",
    children = listOf("Interest Income", "Finances Cost", "Depreciation & Amortisation")
)

private fun beforeTax() = SmeEntryField("Profit before tax", isAggregate = true)

private fun tax() = SmeEntryField("Income tax expense")

private fun verdict() = SmeEntryField("Profit/(Loss) for the period", isAggregate = true)