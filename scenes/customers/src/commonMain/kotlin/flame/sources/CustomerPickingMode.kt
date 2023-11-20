@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import flame.CustomerScenesConfig
import flame.CustomersApi
import kotlin.js.JsExport

class CustomerPickingMode(config: CustomerScenesConfig<CustomersApi>) : EntityPickingMode(config)