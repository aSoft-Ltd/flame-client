package flame.fields

import symphony.PhoneOutput

class SendSmsOutput(
    var phone: PhoneOutput?,
    var message: String?
)