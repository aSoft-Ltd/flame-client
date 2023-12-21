@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package flame.routes.uid

import flame.CustomerScenesConfig
import flame.CustomersApiProvider
import kotlinx.JsExport

class CustomerDuplicateScene(
    config: CustomerScenesConfig<CustomersApiProvider>
) : EntityDuplicateScene(config) {
    override val entity = "Customer"
    override val api = config.api.customers
}