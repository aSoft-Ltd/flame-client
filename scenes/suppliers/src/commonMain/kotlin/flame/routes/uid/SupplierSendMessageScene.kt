@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.SupplierScenesConfig
import flame.SuppliersApiProvider
import kotlin.js.JsExport

class SupplierSendMessageScene(
    config: SupplierScenesConfig<SuppliersApiProvider>
) : SendMessageScene(config) {
    override val api = config.api.suppliers
}