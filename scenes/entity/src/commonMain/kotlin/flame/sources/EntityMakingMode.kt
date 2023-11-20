@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import flame.EntitiesApi
import flame.EntitiesApiProvider
import flame.EntityScenesConfig
import flame.forms.EntityFormScene
import geo.Country
import identifier.CorporatePresenter
import identifier.IndividualPresenter
import identifier.LegalEntityPresenter
import identifier.fields.CorporateFields
import identifier.fields.IndividualFields
import identifier.transformers.toParams
import identifier.transformers.toPresenter
import kase.bagOf
import koncurrent.Later
import koncurrent.toLater
import symphony.toForm
import symphony.toSubmitConfig
import kotlin.js.JsExport

abstract class EntityMakingMode(
    val config: EntityScenesConfig<EntitiesApiProvider>
) : EntityFormScene(config), EntityFieldMode {

    private val handler = bagOf<(LegalEntityPresenter) -> Unit>()

    abstract override val api: EntitiesApi

    fun initialize(callback: ((LegalEntityPresenter) -> Unit)): Later<Any> {
        handler.value = callback
        return load(null)
    }

    override fun corporateForm(
        country: Country,
        entity: CorporatePresenter?
    ) = CorporateFields(entity, country).toForm(
        heading = "Corporate ${this.entity} Form",
        details = "Add Corporate ${this.entity}",
        config = config.toSubmitConfig()
    ) {
        onCancel { deInitialize() }
        onSubmit { output ->
            output.toLater().then {
                it.toParams().getOrThrow()
            }.andThen {
                api.create(it)
            }.then {
                it.toPresenter()
            }
        }
        onSuccess { it: CorporatePresenter ->
            handler.value?.invoke(it)
            dispatchSuccess(it.name, scene = "create")
        }
        onFailure { dispatchFailure(it, scene = "create") }
    }

    override fun individualForm(
        country: Country,
        entity: IndividualPresenter?
    ) = IndividualFields(entity, country).toForm(
        heading = "Individual ${this.entity} Form",
        details = "Add Individual ${this.entity}",
        config = config.toSubmitConfig()
    ) {
        onSubmit { output ->
            output.toLater().then {
                it.toParams().getOrThrow()
            }.andThen {
                api.create(it)
            }.then {
                it.toPresenter()
            }
        }
        onSuccess { it: IndividualPresenter -> dispatchSuccess(it.name, scene = "create") }
        onFailure { dispatchFailure(it, scene = "create") }
    }
}