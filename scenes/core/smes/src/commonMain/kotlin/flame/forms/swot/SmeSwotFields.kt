@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.forms.swot

import kotlinx.JsExport
import symphony.Fields
import symphony.text

class SmeSwotFields(component: SwotComponent, output: SmeSwotOutput) : Fields<SmeSwotOutput>(output) {
    val input = text(
        name = output::input,
        label = "enter a $component"
    )
}