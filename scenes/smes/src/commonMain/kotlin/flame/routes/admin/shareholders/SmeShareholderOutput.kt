package flame.routes.admin.shareholders

class SmeShareholderOutput(
    var name: String? = null,
    var currentShareholding: Double? = null,
    var postInvestmentShareholding: Double? = null,
    var isJuristic: Boolean? = null,
    var beneficiary: String? = null
)