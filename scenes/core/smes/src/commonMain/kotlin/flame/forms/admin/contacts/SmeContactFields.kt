@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.forms.admin.contacts

import kotlinx.JsExport
import krono.date
import symphony.Fields
import symphony.email
import symphony.name
import symphony.phone
import symphony.text

class SmeContactFields(output: SmeContactOutput) : Fields<SmeContactOutput>(output) {
    val firstName = name(
        name = output::firstName,
        label = "First Name"
    )

    val lastName = name(
        name = output::lastName,
        label = "Last Name"
    )

    val email = email(
        name = output::email,
    )

    val phone = phone(
        name = output::phone,
    )

    val role = text(
        name = output::role
    )

    val dob = date(
        name = output::dob
    )
}