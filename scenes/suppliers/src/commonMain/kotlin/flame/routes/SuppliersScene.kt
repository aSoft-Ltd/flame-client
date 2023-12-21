@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes

import flame.SupplierScenesConfig
import flame.SuppliersApiProvider
import kotlinx.JsExport

class SuppliersScene(
    config: SupplierScenesConfig<SuppliersApiProvider>
) : EntitiesScene(config.map { it.suppliers })