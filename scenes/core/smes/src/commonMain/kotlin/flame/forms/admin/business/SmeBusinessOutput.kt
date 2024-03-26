package flame.forms.admin.business

import flame.SmeDto
import geo.AddressOutput
import identifier.Industry
import symphony.PhoneOutput

class SmeBusinessOutput(
    val src: SmeDto,
    var name: String? = null,
    var registration: String? = null,
    var phone: PhoneOutput? = null,
    var yearsInOperation: Int? = null,
    var address: AddressOutput? = null,
    var numberOfJobs: Int? = null,
    var industry: Industry? = null,
    var businessStage: String? = null,
    var bbbee: String? = null,
    var staffComplement: String? = null,
    var description: String? = null
)