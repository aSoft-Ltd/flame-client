@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import kotlin.js.JsExport

interface SupplierScenesConfig<out A> : EntityScenesConfig<A> {
    override fun <R> map(transformer: (A) -> R): SupplierScenesConfig<R>
}