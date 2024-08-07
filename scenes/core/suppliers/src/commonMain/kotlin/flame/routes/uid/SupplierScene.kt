@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.SupplierScenesConfig
import flame.SuppliersApi
import kotlinx.JsExport

class SupplierScene(
    config: SupplierScenesConfig<SuppliersApi>
) : EntityScene(config)