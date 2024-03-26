package flame.forms.financial.office

import flame.SmeDto

class SmeBackOfficeOutput(
    val src: SmeDto,
    var accounting: String? = null,
    var payroll: String? = null,
    var accountingConsultation: String? = null,
    var noOfEmployeesInTheFinanceDepartment: Int? = null,
    var financialHead: String? = null,
    var totalStaffCompliment: Int? = null,
    var hrConsultation: String? = null,
    var policyExistence: String? = null,
    var policyReviewFrequency: String? = null,
    var assetsAssurance: String? = null,
    var criticalSystems: String? = null
)