@file:Suppress("NON_EXPORTABLE_TYPE")
@file:JsExport

package flame.routes.uid

import flame.CustomerScenesConfig
import flame.CustomersApi
import kotlin.js.JsExport

class CustomerGenerateStatementScene(
    config: CustomerScenesConfig<CustomersApi>
) : GenerateStatementScene(config) {
    override val entity = "Customer"
}