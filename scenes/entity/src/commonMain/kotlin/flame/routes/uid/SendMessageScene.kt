@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import cinematic.LazyScene
import flame.EntitiesApi
import flame.EntitiesApiProvider
import flame.EntityScenesConfig
import flame.fields.CommunicationFields
import flame.fields.SendEmailFields
import flame.fields.SendSmsFields
import flame.transformers.toParams
import flame.utils.loadingCustomer
import geo.Country
import identifier.LegalEntityPresenter
import identifier.transformers.toPresenter
import identifier.utils.loadCacheableLegalEntity
import kase.Loading
import kase.Pending
import kase.Success
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import symphony.FormField
import symphony.toForm
import symphony.toSubmitConfig
import kotlinx.JsExport

abstract class SendMessageScene(
    private val config: EntityScenesConfig<EntitiesApiProvider>
) : LazyScene<FormField<Any, CommunicationFields<Any>>>(Pending) {

    protected abstract val api: EntitiesApi

    fun initialize(uid: String): Later<Any> = config.map { api }.loadCacheableLegalEntity(uid) {
        ui.value = loadingCustomer(uid, "message form")
    }.andThen {
        ui.value = Loading("loading your preferences")
        config.api.settings(it)
    }.then {
        sendEmailForm(it.data.toPresenter(), it.country)
    }.finally {
        ui.value = it.toLazyState()
    }

    private fun sendEmailForm(entity: LegalEntityPresenter, country: Country) = SendEmailFields(entity, country).toForm(
        heading = "Send email",
        details = "Send email to ${entity.name}",
        config = config.toSubmitConfig()
    ) {
        onCancel { deInitialize() }
        onSubmit {
            val params = it.toParams(entity).getOrThrow()
            api.sendEmail(params.copy(customerId = entity.uid))
        }
    }

    private fun sendSmsForm(entity: LegalEntityPresenter, country: Country) = SendSmsFields(entity, country).toForm(
        heading = "Send SMS",
        details = "Send sms to ${entity.name}",
        config = config.toSubmitConfig()
    ) {
        onCancel { deInitialize() }
        onSubmit { api.sendSMS(it.toParams(customerId = entity.uid)) }
    }

    fun switchToEmail() {
        val fields = ui.value.data?.fields ?: return
        ui.value = Success(sendEmailForm(fields.recipient, fields.country))
    }

    fun switchToSms() {
        val fields = ui.value.data?.fields ?: return
        ui.value = Success(sendSmsForm(fields.recipient, fields.country))
    }
}