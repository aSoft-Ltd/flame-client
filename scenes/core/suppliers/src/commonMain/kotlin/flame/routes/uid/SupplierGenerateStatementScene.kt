@file:Suppress("NON_EXPORTABLE_TYPE")
@file:JsExport

package flame.routes.uid

import flame.SupplierScenesConfig
import flame.SuppliersApi
import kotlinx.JsExport

class SupplierGenerateStatementScene(
    config: SupplierScenesConfig<SuppliersApi>
) : GenerateStatementScene(config) {
    override val entity = "Supplier"
}