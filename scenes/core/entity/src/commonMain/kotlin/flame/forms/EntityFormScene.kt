@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.forms

import cinematic.LazyScene
import flame.EntitiesApi
import flame.EntitiesApiProvider
import flame.EntityScenesConfig
import flame.utils.dispatchInvalidateCache
import geo.Country
import hormone.Loader
import identifier.CorporatePresenter
import identifier.IdentifierSettings
import identifier.IndividualPresenter
import identifier.LegalEntityDto
import identifier.LegalEntityPresenter
import identifier.fields.CorporateFields
import identifier.fields.IndividualFields
import identifier.fields.LegalEntityFields
import identifier.toCorporate
import identifier.toIndividual
import identifier.transformers.toPresenter
import identifier.utils.loadCacheableLegalEntityOrNull
import identifier.utils.loading
import kase.Pending
import kase.Success
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import koncurrent.later.finally
import symphony.FormField
import kotlinx.JsExport

abstract class EntityFormScene(
    private val config: EntityScenesConfig<EntitiesApiProvider>
) : LazyScene<FormField<LegalEntityPresenter, LegalEntityFields<*>>>(Pending) {

    protected abstract val api: EntitiesApi

    abstract val entity: String

    var original: IdentifierSettings<LegalEntityPresenter?>? = null

    protected fun load(uid: String?): Later<Any> = config.map { api }.loadCacheableLegalEntityOrNull(uid) {
        ui.value = loading(uid, "form")
    }.andThen {
        ui.value = loading(uid, "settings")
        config.api.settings(it?.toPresenter())
    }.then {
        original = it
        when (val entity = it.data) {
            is CorporatePresenter -> corporateForm(it.country, entity)
            else -> individualForm(it.country, entity as? IndividualPresenter)
        }
    }.finally {
        ui.value = it.toLazyState()
    }

    fun switchToCorporateForm() {
        val og = original ?: return
        ui.value = Success(corporateForm(og.country, og.data?.toCorporate()))
    }

    fun switchToIndividualForm() {
        val og = original ?: return
        ui.value = Success(individualForm(og.country, og.data?.toIndividual()))
    }

    override fun deInitialize() {
        original = null
        super.deInitialize()
    }

    protected abstract fun individualForm(
        country: Country,
        entity: IndividualPresenter?
    ): FormField<LegalEntityPresenter, IndividualFields>

    protected abstract fun corporateForm(
        country: Country,
        entity: CorporatePresenter?
    ): FormField<LegalEntityPresenter, CorporateFields>

    protected fun dispatchSuccess(name: String, scene: String) {
        config.localBus.dispatchInvalidateCache()
        config.toaster.makeNewSuccess("Success").withBody("$name ${scene}d successfully").show()
    }

    protected fun dispatchFailure(cause: Throwable, scene: String) {
        config.toaster.makeNewError("Failed to $scene $entity").withBody(cause.message ?: "Unknown error").show()
    }
}