@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.SupplierScenesConfig
import flame.SuppliersApiProvider
import kotlinx.JsExport

class SupplierEditScene(
    config: SupplierScenesConfig<SuppliersApiProvider>
) : EntityEditScene(config) {
    override val entity = "Supplier"
    override val api = config.api.suppliers
}