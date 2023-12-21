package flame.transformers.finance

import flame.SmeApi
import flame.SmeSceneOption
import flame.finance.SmeFinancialStatusDto
import flame.routes.financial.status.SmeFinancialStatusFields
import flame.routes.financial.status.SmeFinancialStatusOutput
import flame.transformers.toPresenter
import flame.transformers.utils.toProgress
import kollections.listOf
import koncurrent.toLater
import symphony.toForm

internal fun SmeFinancialStatusDto?.toOutput() = SmeFinancialStatusOutput(
    latestFinancialStatements = this?.latestFinancialStatements,
    managementAccounts = this?.managementAccounts,
    financialsAuditedOrReviewed = this?.financialsAuditedOrReviewed,
    budgetForReview = this?.budgetForReview,
    debtorsAging = this?.debtorsAging,
    creditAging = this?.creditAging,
    longTermContracts = this?.longTermContracts,
    offBalanceSheetFunding = this?.offBalanceSheetFunding,
    assetRegister = this?.assetRegister,
    permissionsFromLender = this?.permissionsFromLender,
    guarantees = this?.guarantees,
)

internal fun SmeFinancialStatusOutput.toParams() = SmeFinancialStatusDto(
    latestFinancialStatements = latestFinancialStatements,
    managementAccounts = managementAccounts,
    financialsAuditedOrReviewed = financialsAuditedOrReviewed,
    budgetForReview = budgetForReview,
    debtorsAging = debtorsAging,
    creditAging = creditAging,
    longTermContracts = longTermContracts,
    offBalanceSheetFunding = offBalanceSheetFunding,
    assetRegister = assetRegister,
    permissionsFromLender = permissionsFromLender,
    guarantees = guarantees,
)

fun SmeFinancialStatusDto?.toForm(options: SmeSceneOption<SmeApi>) = SmeFinancialStatusFields(toOutput()).toForm(
    heading = "Financial Status",
    details = "Enter the current status of your financial predicaments",
    logger = options.logger
) {
    onSubmit { output ->
        output.toLater().then {
            output.toParams()
        }.andThen {
            options.api.finance.update(it)
        }.then {
            it.toPresenter()
        }
    }

    onSuccess { options.bus.dispatch(options.topic.progressMade()) }
}

fun SmeFinancialStatusDto?.toProgress() = listOf(
    this?.latestFinancialStatements,
    this?.managementAccounts,
    this?.financialsAuditedOrReviewed,
    this?.budgetForReview,
    this?.debtorsAging,
    this?.creditAging,
    this?.longTermContracts,
    this?.offBalanceSheetFunding,
    this?.assetRegister,
    this?.permissionsFromLender,
    this?.guarantees,
).toProgress()