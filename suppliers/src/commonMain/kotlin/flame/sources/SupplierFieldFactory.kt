package flame.sources

import flame.SupplierPresenter
import flame.SupplierScenesConfig
import flame.SuppliersApiProvider
import neat.ValidationFactory
import symphony.Changer
import symphony.Fields
import symphony.Visibility
import kotlin.reflect.KMutableProperty0
import symphony.Visibilities

fun Fields<*>.supplier(
    name: KMutableProperty0<SupplierPresenter?>,
    config: SupplierScenesConfig<SuppliersApiProvider>,
    label: String = name.name,
    visibility: Visibility = Visibilities.Hidden,
    onChange: Changer<SupplierPresenter>? = null,
    factory: ValidationFactory<SupplierPresenter>? = null
): SupplierField = getOrCreate(name) {
    SupplierField(name, config, label, visibility, onChange, factory)
}