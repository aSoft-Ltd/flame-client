package flame.forms.admin.contacts

import flame.SmeDto
import krono.LocalDate
import symphony.PhoneOutput

class SmeContactOutput(
    val src: SmeDto,
    var firstName: String? = "",
    var lastName: String? = "",
    var email: String? = "",
    var phone: PhoneOutput? = null,
    var role: String? = "",
    var dob: LocalDate? = null
)