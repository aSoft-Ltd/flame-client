package flame.forms.financial.status

import flame.SmeDto

class SmeFinancialStatusOutput(
    val src: SmeDto,
    var latestFinancialStatements: String?,
    var managementAccounts: String?,
    var financialsAuditedOrReviewed: String?,
    var budgetForReview: String?,
    var debtorsAging: String?,
    var creditAging: String?,
    var longTermContracts: String?,
    var offBalanceSheetFunding: String?,
    var assetRegister: String?,
    var permissionsFromLender: String?,
    var guarantees: String?,
)