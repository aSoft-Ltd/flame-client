@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.finance

import flame.SmeDto
import flame.SmeSceneOption
import flame.SmeScheme
import flame.finance.SmeFinanceDto
import flame.finance.SmeFinancialStatusDto
import flame.forms.financial.status.SmeFinancialStatusFields
import flame.forms.financial.status.SmeFinancialStatusOutput
import flame.transformers.toPresenter
import flame.transformers.utils.toProgress
import kollections.listOf
import koncurrent.later.andThen
import koncurrent.later.then
import koncurrent.toLater
import symphony.toForm

internal inline fun SmeDto.toFinancialStatusOutput() = finance?.status.toOutput(this)
internal inline fun SmeFinancialStatusDto?.toOutput(src: SmeDto) = SmeFinancialStatusOutput(
    src = src,
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

internal inline fun SmeFinancialStatusOutput.toParams() = SmeFinancialStatusDto(
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
).let {
    src.copy(finance = (src.finance ?: SmeFinanceDto()).copy(status = it))
}

fun SmeDto.toFinancialStatusForm(
    options: SmeSceneOption<SmeScheme>,
    details: String
) = SmeFinancialStatusFields(toFinancialStatusOutput()).toForm(
    heading = "Financial Status",
    details = details,
    logger = options.logger
) {
    onSubmit { output ->
        output.toLater().then {
            output.toParams()
        }.andThen {
            options.api.update(it)
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