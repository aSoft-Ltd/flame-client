@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.funding.breakdown

import kotlin.js.JsExport
import symphony.Fields
import symphony.double

class SmeBreakdownFields(output: SmeBreakdownOutput) : Fields<SmeBreakdownOutput>(output) {
    val acquisition = double(
        name = output::acquisition,
        label = "Business Acquisition",
        hint = "Enter Business Acquisition"
    )
    val capex = double(
        name = output::capex,
        label = "CAPEX (Plants & Equipment)",
        hint = "Enter CAPEX (Plants & Equipment"
    )
    val capital = double(
        name = output::capital,
        label = "Working Capital",
        hint = "Enter Working Capital"
    )
    val finance = double(
        name = output::finance,
        label = "Project Finance",
        hint = "Enter Project Finance"
    )
}