package flame.routes.admin.legal

import flame.forms.admin.business.SmeBusinessOutput

class SmeLegalOutput(
    var cipcAnnualReturns: String? = null,
    /**
     * What is the difference between this and [SmeBusinessOutput.registration]
     */
    var registration: String? = null,
    var vatRegistration: String? = null,
    var vatNumber: String? = null,
    var taxComplianceStatus: String? = null,
    var incomeTaxNumber: Int? = null,
    var workmanCompensationOption: String? = null,
    var workmanCompensationNumber: Int? = null
)