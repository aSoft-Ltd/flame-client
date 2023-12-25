@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.swot

import kotlinx.JsExport
import symphony.Fields
import symphony.text

class SmeSwotFields(component: String,output: SmeSwotOutput) : Fields<SmeSwotOutput>(output) {
    val input = text(
        name = output::input,
        label = "enter a $component"
    )
}