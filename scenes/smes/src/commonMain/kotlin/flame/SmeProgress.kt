@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import kotlin.js.JsExport

data class SmeProgress(
    val sme: SmePresenter,
    val admin: SmeSectionProgress,
    val funding: SmeSectionProgress,
    val documents: SmeSectionProgress,
) {
    val overall by lazy { admin + funding + documents }
}