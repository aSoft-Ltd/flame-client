@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import flame.SupplierScenesConfig
import flame.SuppliersApi
import kotlin.js.JsExport

class SupplierPickingMode(config: SupplierScenesConfig<SuppliersApi>) : EntityPickingMode(config)