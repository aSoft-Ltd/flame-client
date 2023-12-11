@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin.directors

import kotlin.js.JsExport
import neat.required
import symphony.Fields
import symphony.email
import symphony.name
import symphony.phone

class SmeDirectorFields(output: SmeDirectorOutput) : Fields<SmeDirectorOutput>(output) {
    val name = name(
        name = output::name
    )  { required() }

    val email = email(
        name = output::email,
    )

    val phone = phone(
        name = output::phone,
    )
}