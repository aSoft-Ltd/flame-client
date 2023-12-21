@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import kotlinx.JsExport

data class SmeProgress(
    val sme: SmePresenter,
    val admin: SmeSectionProgress,
    val documents: SmeSectionProgress,
    val finance: SmeSectionProgress,
    val funding: SmeSectionProgress,
    val governance: SmeSectionProgress,
    val swot: SmeSectionProgress
) {
    val overall by lazy { admin + documents + finance + funding + governance }
}