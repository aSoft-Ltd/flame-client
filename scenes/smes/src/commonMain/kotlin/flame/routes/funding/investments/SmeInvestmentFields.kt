package flame.routes.funding.investments

import symphony.Fields
import symphony.Option
import symphony.double
import symphony.selectSingle
import symphony.text

class SmeInvestmentFields(output: SmeInvestmentOutput) : Fields<SmeInvestmentOutput>(output) {
    val amount = double(
        name = output::amount,
        label = "Investment Amount Required",
        hint = "Enter Investment Amount Required"
    )

    val type = selectSingle(
        name = output::type,
        label = "Type of Investment",
        items = listOf("Equity", "Debt", "Equity & Debt"),
        mapper = { Option(it, it) }
    )

    val debtTenure = double(
        name = output::debtTenure,
        label = "Debt Tenure",
        hint = "Enter Debt Tenure"
    )

    val equity = double(
        name = output::equity,
        label = "% Equity Shareholder on Offer",
        hint = "Enter % Equity Shareholder on Offer"
    )

    val description = text(
        name = output::description,
        label = "Brief Description of the business and activities",
        hint = "Enter Brief Description of the business and activities"
    )
}