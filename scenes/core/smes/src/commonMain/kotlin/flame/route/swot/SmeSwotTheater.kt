@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.route.swot

import kotlinx.JsExport

sealed interface SmeSwotTheater {
    val strengths: SmeSwotComponentScene

    val weaknesses: SmeSwotComponentScene

    val opportunities: SmeSwotComponentScene

    val threats: SmeSwotComponentScene
}