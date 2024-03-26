@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.forms.admin.legal

import flame.forms.selectCompliance
import kotlinx.JsExport
import symphony.Fields
import kollections.listOf
import symphony.Option
import symphony.integer
import symphony.selectSingle
import symphony.text

class SmeLegalFields(output: SmeLegalOutput) : Fields<SmeLegalOutput>(output) {
    val cipcAnnualReturns = selectCompliance(
        name = output::cipcAnnualReturns,
        label = "CIPC Annual Returns",
        hint = "Select Compliance Option",
    )

    val registration = text(
        name = output::registration,
        label = "Company Registration Number",
        hint = "Enter Company Registration Number"
    )

    val vatRegistration = selectSingle(
        name = output::vatRegistration,
        label = "VAT Registration",
        hint = "Select VAT Registration Option",
        items = listOf("Yes", "No"),
        mapper = { Option(it, it) }
    )

    val vatNumber = text(
        name = output::vatNumber,
        label = "VAT Number",
        hint = "Enter VAT Number"
    )

    val taxComplianceStatus = selectCompliance(
        name = output::taxComplianceStatus,
        label = "TAX Compliance Status",
        hint = "Select TAX Compliance Status",
    )

    val incomeTaxNumber = integer(
        name = output::incomeTaxNumber,
        label = "Income TAX Number",
        hint = "Enter Income Tax Number"
    )

    val workmanCompensationOption = selectCompliance(
        name = output::workmanCompensationOption,
        label = "Workman's Compensation",
        hint = "Select Workman's Compensation Option"
    )

    val workmanCompensationNumber = integer(
        name = output::workmanCompensationNumber,
        label = "Workman's Compensation Number",
        hint = "Enter Workman's Compensation Number"
    )
}