@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import flame.SupplierScenesConfig
import flame.SuppliersApi
import kotlinx.JsExport

class SupplierPickingMode(config: SupplierScenesConfig<SuppliersApi>) : EntityPickingMode(config)