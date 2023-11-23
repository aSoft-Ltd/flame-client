@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import kotlin.js.JsExport

data class SmeProgress(
    val sme: SmeDto,
    val admin: SmeSectionProgress,
    val funding: SmeSectionProgress,
)