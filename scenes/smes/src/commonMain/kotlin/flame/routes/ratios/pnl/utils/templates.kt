package flame.routes.ratios.pnl.utils

import flame.routes.ratios.utils.SmeEntryField
import kollections.iListOf

internal fun defaultTemplate() = iListOf(revenue(), expenses(), ebitda(), beforeTax(), tax(), verdict())

private fun revenue() = SmeEntryField(
    name = "Revenue",
    children = iListOf(
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
    children = iListOf("Administrative Expenses", "Other Operating Expenses", "Salary & Staff Cost")
)

private fun ebitda() = SmeEntryField(
    name = "EBITDA",
    children = iListOf("Interest Income", "Finances Cost", "Depreciation & Amortisation")
)

private fun beforeTax() = SmeEntryField("Profit before tax", isAggregate = true)

private fun tax() = SmeEntryField("Income tax expense")

private fun verdict() = SmeEntryField("Profit/(Loss) for the period", isAggregate = true)