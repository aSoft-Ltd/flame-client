package flame.sources

import flame.CustomerScenesConfig
import flame.CustomerPresenter
import flame.CustomersApiProvider
import neat.ValidationFactory
import symphony.Fields
import symphony.Visibility
import symphony.Changer
import kotlin.reflect.KMutableProperty0
import symphony.Visibilities

fun Fields<*>.customer(
    name: KMutableProperty0<CustomerPresenter?>,
    config: CustomerScenesConfig<CustomersApiProvider>,
    label: String = name.name,
    visibility: Visibility = Visibilities.Hidden,
    onChange: Changer<CustomerPresenter>? = null,
    factory: ValidationFactory<CustomerPresenter>? = null
): CustomerField = getOrCreate(name) {
    CustomerField(name, config, label, visibility, onChange, factory)
}