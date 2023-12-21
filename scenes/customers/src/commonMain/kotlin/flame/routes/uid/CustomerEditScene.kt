@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.CustomerScenesConfig
import flame.CustomersApiProvider
import kotlinx.JsExport

class CustomerEditScene(
    config: CustomerScenesConfig<CustomersApiProvider>
) : EntityEditScene(config) {
    override val entity = "Customer"
    override val api = config.api.customers
}