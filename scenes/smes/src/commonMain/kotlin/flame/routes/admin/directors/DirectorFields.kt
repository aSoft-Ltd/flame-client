@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin.directors

import kotlin.js.JsExport
import symphony.Fields
import symphony.name

class DirectorFields(output: DirectorOutput) : Fields<DirectorOutput>(output) {
    val name = name(
        name = output::name
    )
}