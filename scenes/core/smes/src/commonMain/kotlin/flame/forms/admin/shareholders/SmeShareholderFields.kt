@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.forms.admin.shareholders

import kotlinx.JsExport
import neat.required
import symphony.Fields
import symphony.boolean
import symphony.double
import symphony.name
import symphony.text

class SmeShareholderFields(output: SmeShareholderOutput) : Fields<SmeShareholderOutput>(output) {
    val name = name(
        name = output::name,
        label = "Full Name of Shareholder",
        hint = "Enter Full Name of Shareholder"
    ) { required() }

    val currentShareholding = double(
        name = output::currentShareholding,
        label = "Current Shareholding",
        hint = "Enter Current Shareholding (%)"
    )
    val postInvestmentShareholding = double(
        name = output::postInvestmentShareholding,
        label = "Post Investment Shareholding",
        hint = "Enter Post Investment Shareholding (%)"
    )
    val isJuristic = boolean(
        name = output::isJuristic,
        label = "Is this Shareholder a Juristic Person?"
    )
    val beneficiary = text(
        name = output::beneficiary,
        label = "Ultimate Beneficiary",
        hint = "Please provide details of the ultimate beneficiary"
    )
}