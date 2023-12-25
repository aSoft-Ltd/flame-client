@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package flame.routes.add

import flame.CustomerScenesConfig
import flame.CustomersApiProvider
import kotlinx.JsExport

class CustomerAddScene(
    config: CustomerScenesConfig<CustomersApiProvider>
) : EntityAddScene(config) {
    override val entity = "Customer"
    override val api = config.api.customers
}