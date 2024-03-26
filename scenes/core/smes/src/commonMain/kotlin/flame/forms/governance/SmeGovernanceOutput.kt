package flame.forms.governance

import flame.SmeDto

class SmeGovernanceOutput(
    val src: SmeDto,
    var insuranceScheme: String?,
    var noOfJobs: Int?,
    var skillShortfall: String?,
    var labour: String?,
    var unionised: String?,
    var successPlan: String?,
    var organogram: String?,
    var disputes: String?,
    var specialist: String?,
)