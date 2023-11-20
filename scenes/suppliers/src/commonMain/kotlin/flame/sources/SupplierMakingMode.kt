@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import flame.SupplierScenesConfig
import flame.SuppliersApiProvider
import kotlin.js.JsExport

class SupplierMakingMode(
    config: SupplierScenesConfig<SuppliersApiProvider>
) : EntityMakingMode(config) {
    override val entity = "Supplier"
    override val api = config.api.suppliers
}