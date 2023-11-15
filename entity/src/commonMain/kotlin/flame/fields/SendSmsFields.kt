@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.fields

import flame.transformers.toSendSmsOutput
import geo.Country
import identifier.LegalEntityPresenter
import neat.notBlank
import neat.required
import symphony.phone
import symphony.text
import kotlin.js.JsExport
import flame.fields.SendSmsOutput as Output

class SendSmsFields(
    recipient: LegalEntityPresenter,
    country: Country
) : CommunicationFields<Output>(recipient, country, recipient.toSendSmsOutput()) {
    val phone = phone(
        name = output::phone,
        country = country
    ) { required() }

    val body = text(output::message) {
        notBlank()
        required()
    }
}