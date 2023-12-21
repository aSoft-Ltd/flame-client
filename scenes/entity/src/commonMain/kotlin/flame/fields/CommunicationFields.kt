@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.fields

import geo.Country
import identifier.LegalEntityPresenter
import symphony.Fields
import kotlinx.JsExport

sealed class CommunicationFields<out P : Any>(val recipient: LegalEntityPresenter, val country: Country, initial: P) : Fields<P>(initial) {
    val asSms get() = this as? SendSmsFields
    val asEmail get() = this as? SendEmailFields
}