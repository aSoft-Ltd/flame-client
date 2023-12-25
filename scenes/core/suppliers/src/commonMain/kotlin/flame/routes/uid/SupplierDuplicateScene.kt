@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.SupplierScenesConfig
import flame.SuppliersApiProvider
import kotlinx.JsExport

class SupplierDuplicateScene(
    config: SupplierScenesConfig<SuppliersApiProvider>
) : EntityDuplicateScene(config) {
    override val entity = "Supplier"
    override val api = config.api.suppliers
}