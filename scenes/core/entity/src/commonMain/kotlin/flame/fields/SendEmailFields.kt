@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.fields

import flame.transformers.toEmailOutput
import geo.Country
import identifier.LegalEntityPresenter
import neat.notBlank
import neat.required
import symphony.email
import symphony.name
import symphony.text
import kotlinx.JsExport

class SendEmailFields(
    recipient: LegalEntityPresenter,
    country: Country
) : CommunicationFields<SendEmailOutput>(recipient, country, recipient.toEmailOutput()) {
    val email = email(name = output::email) { required() }
    val subject = name(name = output::subject) { notBlank().required() }

    val body = text(
        name = output::body,
        label = "Email message",
        hint = "Email goes here message"
    ) { notBlank().required() }
}