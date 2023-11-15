@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import flame.CustomerScenesConfig
import flame.CustomersApiProvider
import kotlin.js.JsExport

class CustomerMakingMode(
    config: CustomerScenesConfig<CustomersApiProvider>
) : EntityMakingMode(config) {
    override val entity = "Customers"
    override val api = config.api.customers
}