@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.funding.acquisition

import kotlin.js.JsExport
import symphony.Fields
import symphony.double
import symphony.text

class SmeAcquisitionFields(output: SmeAcquisitionOutput) : Fields<SmeAcquisitionOutput>(output) {
    val price = double(
        name = output::price,
        label = "What is the asking price of target company?",
        hint = "Enter Price",
    )

    val dd = double(
        name = output::dd,
        label = "What value has your DD produced?",
        hint = "Enter DD value"
    )

    val valuation = text(
        name = output::valuation,
        label = "What is the valuation method used?",
        hint = "Enter valuation method"
    )
}