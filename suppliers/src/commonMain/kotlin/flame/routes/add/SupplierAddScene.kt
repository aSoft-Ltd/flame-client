@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package flame.routes.add

import flame.SupplierScenesConfig
import flame.SuppliersApiProvider
import kotlin.js.JsExport

class SupplierAddScene(
    config: SupplierScenesConfig<SuppliersApiProvider>
) : EntityAddScene(config) {
    override val entity = "Supplier"
    override val api = config.api.suppliers
}