@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import flame.SupplierScenesConfig
import flame.SuppliersApiProvider
import identifier.LegalEntityPresenter
import neat.ValidationFactory
import symphony.Changer
import symphony.Visibility
import kotlin.js.JsExport
import kotlin.reflect.KMutableProperty0

class SupplierField(
    property: KMutableProperty0<LegalEntityPresenter?>,
    private val config: SupplierScenesConfig<SuppliersApiProvider>,
    label: String,
    visibility: Visibility,
    onChanger: Changer<LegalEntityPresenter>?,
    factory: ValidationFactory<LegalEntityPresenter>?,
) : EntityField(property, label, visibility, onChanger, factory) {

    override fun picking(): SupplierPickingMode {
        val ps = SupplierPickingMode(config.map { it.suppliers })
        ps.initialize { set(it) }
        return ps
    }

    override fun making(): SupplierMakingMode {
        val ms = SupplierMakingMode(config)
        ms.initialize { set(it) }
        return ms
    }
}