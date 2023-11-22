@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin.business

import geo.address
import identifier.Industry
import kotlin.js.JsExport
import symphony.Fields
import symphony.integer
import symphony.phone
import symphony.selectSingle
import symphony.text
import symphony.toOption

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
        label = "Physical Address"
    )

    val jobs = integer(
        name = output::numberOfJobs,
        label = "Number of Jobs to be created"
    )

    val industry = selectSingle(
        name = output::industry,
        items = Industry.values().toList(),
        mapper = { it.toOption() }
    )

    val stage = text(
        name = output::businessStage,
        label = "Business Stage"
    )

    val bbbee = text(
        name = output::bbbee,
        label = "BBBEE Level"
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