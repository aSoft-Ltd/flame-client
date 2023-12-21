@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import kotlinx.JsExport

sealed interface EntityFieldMode {
    val asPicking get() = this as? EntityPickingMode
    val asMaking get() = this as? EntityMakingMode
}