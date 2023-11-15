@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.CustomerScenesConfig
import flame.CustomersApiProvider
import kotlin.js.JsExport

class CustomerSendMessageScene(
    config: CustomerScenesConfig<CustomersApiProvider>
) : SendMessageScene(config) {
    override val api = config.api.customers
}