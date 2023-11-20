@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import flame.CustomerScenesConfig
import flame.CustomersApiProvider
import identifier.LegalEntityPresenter
import neat.ValidationFactory
import symphony.Changer
import symphony.Visibility
import kotlin.js.JsExport
import kotlin.reflect.KMutableProperty0

class CustomerField(
    property: KMutableProperty0<LegalEntityPresenter?>,
    private val config: CustomerScenesConfig<CustomersApiProvider>,
    label: String,
    visibility: Visibility,
    onChanger: Changer<LegalEntityPresenter>?,
    factory: ValidationFactory<LegalEntityPresenter>?,
) : EntityField(property, label, visibility, onChanger, factory) {

    override fun picking(): CustomerPickingMode {
        val ps = CustomerPickingMode(config.map { it.customers })
        ps.initialize { set(it) }
        return ps
    }

    override fun making(): CustomerMakingMode {
        val ms = CustomerMakingMode(config)
        ms.initialize { set(it) }
        return ms
    }
}