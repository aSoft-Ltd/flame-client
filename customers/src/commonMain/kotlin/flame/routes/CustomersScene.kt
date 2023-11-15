@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes

import flame.CustomerScenesConfig
import flame.CustomersApiProvider
import kotlin.js.JsExport

class CustomersScene(
    config: CustomerScenesConfig<CustomersApiProvider>
) : EntitiesScene(config.map { it.customers })