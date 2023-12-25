@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin.business

import geo.address
import identifier.Industry
import kollections.listOf
import kollections.toList
import symphony.Fields
import symphony.Option
import symphony.integer
import symphony.phone
import symphony.selectSingle
import symphony.text
import kotlinx.JsExport

class SmeBusinessFields(output: SmeBusinessOutput) : Fields<SmeBusinessOutput>(output) {
    val name = text(
        name = output::name,
        label = "Name of Business"
    )

    val registration = text(
        name = output::registration,
        label = "Company Registration"
    )

    val phone = phone(
        name = output::phone,
        label = "Telephone Number"
    )

    val years = integer(
        name = output::yearsInOperation,
        label = "Years In Operation"
    )

    val address = address(
        name = output::address,
        label = "Physical Address",
    )

    val jobs = integer(
        name = output::numberOfJobs,
        label = "Number of Jobs to be created"
    )

    val industry = selectSingle(
        name = output::industry,
        items = Industry.values().toList(),
        mapper = { Option(it.label, it.label) }
    )

    val stage = selectSingle(
        name = output::businessStage,
        label = "Business Stage",
        items = listOf("Startup Phase", "Growth Phase", "Maturity Phase", "Decline/Revitalization"),
        mapper = { Option(it, it) }
    )

    val bbbee = selectSingle(
        name = output::bbbee,
        items = (1..9).map { it.toString() }.toList(),
        mapper = { Option("Level $it") }
    )

    val staffCompliment = text(
        name = output::staffComplement,
        label = "Current Staff Compliment"
    )

    val description = text(
        name = output::description,
        label = "Brief description of the business and activities"
    )
}