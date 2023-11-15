package flame.transformers

import flame.fields.SendEmailOutput
import flame.fields.SendSmsOutput
import flame.params.SendSMSParams
import identifier.LegalEntityPresenter
import identifier.primaryEmail
import identifier.primaryPhone
import identifier.transformers.toOutput

fun LegalEntityPresenter.toSendSmsOutput() = SendSmsOutput(
    phone = primaryPhone?.toOutput(),
    message = ""
)

fun SendSmsOutput.toParams(customerId: String) = SendSMSParams(
    customerId = customerId,
    mobile = phone?.toString(),
    message = message
)

fun LegalEntityPresenter?.toEmailOutput() = SendEmailOutput(
    email = this?.primaryEmail?.value ?: "",
    subject = "",
    body = ""
)