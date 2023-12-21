@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import kotlinx.JsExport

interface SupplierScenesConfig<out A> : EntityScenesConfig<A> {
    override fun <R> map(transformer: (A) -> R): SupplierScenesConfig<R>
}