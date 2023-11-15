@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.EntitiesApi
import flame.EntitiesApiProvider
import flame.EntityScenesConfig
import flame.forms.EntityFormScene
import flame.utils.dispatchInvalidateCache
import geo.Country
import hormone.Identified
import identifier.CorporatePresenter
import identifier.IndividualPresenter
import identifier.fields.CorporateFields
import identifier.fields.IndividualFields
import identifier.transformers.toParams
import identifier.transformers.toPresenter
import koncurrent.Later
import koncurrent.toLater
import symphony.toForm
import symphony.toSubmitConfig
import kotlin.js.JsExport

abstract class EntityEditScene(
    private val config: EntityScenesConfig<EntitiesApiProvider>
) : EntityFormScene(config) {

    abstract override val api: EntitiesApi

    fun initialize(uid: String) = load(uid)

    override fun individualForm(
        country: Country,
        entity: IndividualPresenter?
    ) = IndividualFields(entity, country).toForm(
        heading = "Individual ${this.entity} Form",
        details = "Edit ${entity?.name}'s Info",
        config.toSubmitConfig()
    ) {
        onCancel { deInitialize() }
        onSubmit { output ->
            entity?.uid.toString().toLater().then {
                Identified(it, output.toParams().getOrThrow())
            }.andThen {
                api.editIndividual(it)
            }.then {
                it.toPresenter()
            }
        }
        onSuccess { it: IndividualPresenter -> dispatchSuccess(it.name, scene = "update") }
        onFailure { dispatchFailure(it, scene = "update") }
    }

    override fun corporateForm(
        country: Country,
        entity: CorporatePresenter?
    ) = CorporateFields(entity, country).toForm(
        heading = "Corporate ${this.entity} Form",
        details = "Edit ${entity?.name}'s Info",
        config.toSubmitConfig()
    ) {
        onCancel { deInitialize() }
        onSubmit { output ->
            entity?.uid.toString().toLater().then {
                Identified(it, output.toParams().getOrThrow())
            }.andThen {
                api.editCorporate(it)
            }.then {
                it.toPresenter()
            }
        }
        onSuccess { it: CorporatePresenter -> dispatchSuccess(it.name, scene = "update") }
        onFailure { dispatchFailure(it, scene = "update") }
    }
}