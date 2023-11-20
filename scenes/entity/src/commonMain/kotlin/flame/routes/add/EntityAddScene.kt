@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package flame.routes.add

import flame.EntitiesApiProvider
import flame.EntityScenesConfig
import flame.forms.EntityFormScene
import flame.utils.dispatchInvalidateCache
import geo.Country
import identifier.CorporatePresenter
import identifier.IndividualPresenter
import identifier.fields.CorporateFields
import identifier.fields.IndividualFields
import identifier.transformers.toParams
import identifier.transformers.toPresenter
import kase.Failure
import kase.Success
import koncurrent.later.finally
import koncurrent.toLater
import symphony.toForm
import symphony.toSubmitConfig
import kotlin.js.JsExport

abstract class EntityAddScene(
    private val config: EntityScenesConfig<EntitiesApiProvider>
) : EntityFormScene(config) {
    fun initialize() = load(null)

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
        onSuccess { it: CorporatePresenter -> dispatchSuccess(it.name, "create") }
        onFailure { dispatchFailure(it, "create") }
    }

    override fun individualForm(
        country: Country,
        entity: IndividualPresenter?
    ) = IndividualFields(entity, country).toForm(
        heading = "Individual ${this.entity} Form",
        details = "Add Individual ${this.entity}",
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
        onSuccess { it: IndividualPresenter -> dispatchSuccess(it.name, scene = "create") }
        onFailure { dispatchFailure(it, scene = "create") }
    }
}