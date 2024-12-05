@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package flame.routes.uid

import flame.EntitiesApiProvider
import flame.EntityScenesConfig
import identifier.FieldInfo
import flame.forms.EntityFormScene
import geo.Country
import identifier.CorporatePresenter
import identifier.IndividualPresenter
import identifier.fields.CorporateFields
import identifier.fields.IndividualFields
import identifier.transformers.toOutput
import identifier.transformers.toParams
import identifier.transformers.toPresenter
import kollections.List
import koncurrent.toLater
import koncurrent.later.then
import koncurrent.later.andThen
import symphony.toForm
import symphony.toSubmitConfig
import kotlinx.JsExport

abstract class EntityDuplicateScene(
    private val config: EntityScenesConfig<EntitiesApiProvider>
) : EntityFormScene(config) {

    fun initialize(uid: String) = load(uid)

    override fun corporateForm(
        country: Country,
        entity: CorporatePresenter?,
        additional: List<FieldInfo>
    ) = CorporateFields(entity, entity.toOutput(), country).toForm(
        heading = "Corporate ${this.entity} Form",
        details = "Duplicate ${entity?.name}'s info",
        config.toSubmitConfig()
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
        onSuccess { it: CorporatePresenter -> dispatchSuccess(it.name, scene = "duplicate") }
        onFailure { dispatchFailure(it, scene = "duplicate") }
    }

    override fun individualForm(
        country: Country,
        entity: IndividualPresenter?,
        additional: List<FieldInfo>
    ) = IndividualFields(entity, entity.toOutput(), country).toForm(
        heading = "Individual ${this.entity} Form",
        details = "Duplicate ${entity?.name}'s info",
        config.toSubmitConfig()
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
        onSuccess { it: IndividualPresenter -> dispatchSuccess(it.name, scene = "duplicate") }
        onFailure { dispatchFailure(it, scene = "duplicate") }
    }
}