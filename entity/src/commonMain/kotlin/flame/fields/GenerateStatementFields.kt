@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.fields

import identifier.LegalEntityPresenter
import neat.required
import symphony.Fields
import krono.range
import kotlin.js.JsExport
import flame.fields.GenerateStatementOutput as Output

class GenerateStatementFields(val subject: LegalEntityPresenter) : Fields<Output>(Output(null)) {
    val range = range(
        name = output::range,
        label = "Date Range"
    ) { required() }
}