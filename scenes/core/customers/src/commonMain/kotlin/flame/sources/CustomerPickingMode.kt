@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import flame.CustomerScenesConfig
import flame.CustomersApi
import kotlinx.JsExport

class CustomerPickingMode(config: CustomerScenesConfig<CustomersApi>) : EntityPickingMode(config)