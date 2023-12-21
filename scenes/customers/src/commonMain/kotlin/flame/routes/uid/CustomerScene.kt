@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.CustomerScenesConfig
import flame.CustomersApi
import kotlinx.JsExport

class CustomerScene(
    config: CustomerScenesConfig<CustomersApi>
) : EntityScene(config)