@file:Suppress("NON_EXPORTABLE_TYPE")
@file:JsExport

package flame.routes.uid

import cinematic.LazyScene
import cinematic.mutableLiveOf
import flame.EntitiesApi
import flame.EntityScenesConfig
import flame.fields.GenerateStatementFields
import flame.transformers.toParams
import flame.utils.RemoteLink
import flame.utils.loadingCustomer
import identifier.LegalEntityPresenter
import identifier.transformers.toPresenter
import identifier.utils.loadCacheableLegalEntity
import kase.Pending
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import koncurrent.later.finally
import symphony.FormField
import symphony.toForm
import symphony.toSubmitConfig
import kotlinx.JsExport

abstract class GenerateStatementScene(
    val config: EntityScenesConfig<EntitiesApi>
) : LazyScene<FormField<String, GenerateStatementFields>>(Pending) {

    abstract val entity: String

    protected val api = config.api
    val link = mutableLiveOf<RemoteLink?>(null)

    fun initialize(uid: String): Later<Any> = config.loadCacheableLegalEntity(uid) {
        link.value = null
        ui.value = loadingCustomer(uid, "statement form")
    }.then {
        form(it.toPresenter())
    }.finally {
        ui.value = it.toLazyState()
    }

    override fun deInitialize() {
        link.value = null
        super.deInitialize()
    }

    protected fun form(entity: LegalEntityPresenter) = GenerateStatementFields(entity).toForm(
        heading = "Generate ${this.entity} statement",
        details = "Enter the start and end dates",
        config = config.toSubmitConfig()
    ) {
        onCancel { deInitialize() }
        onSubmit { output ->
            link.value = null
            val params = output.toParams(entity.uid).getOrThrow()
            api.generateStatementURL(params)
        }
        onSuccess {
            link.value = RemoteLink(it, config.clipboard, config.downloader)
        }
    }
}