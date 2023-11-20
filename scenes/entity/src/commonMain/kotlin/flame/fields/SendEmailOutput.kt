package flame.fields

import flame.params.SendEmailParams
import identifier.LegalEntityPresenter
import kase.catching
import neat.required

class SendEmailOutput(
    var email: String?,
    var subject: String?,
    var body: String?
) {
    fun toParams(recipient: LegalEntityPresenter) = catching {
        SendEmailParams(
            customerId = recipient.uid,
            email = ::email.required,
            subject = ::subject.required,
            emailBody = ::body.required
        )
    }
}