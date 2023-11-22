package flame.routes.admin.contacts

import krono.LocalDate
import symphony.PhoneOutput

class SmeContactOutput(
    var firstName: String? = "",
    var lastName: String? = "",
    var email: String? = "",
    var phone: PhoneOutput? = null,
    var role: String? = "",
    var dob: LocalDate? = null
)