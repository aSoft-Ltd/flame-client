@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.forms.financial.status

import flame.forms.selectYesOrNo
import kollections.listOf
import kotlinx.JsExport
import symphony.Fields
import symphony.Option
import symphony.selectSingle
import symphony.text

class SmeFinancialStatusFields(output: SmeFinancialStatusOutput) : Fields<SmeFinancialStatusOutput>(output) {

    val latestFinancialStatements = selectYesOrNo(
        name = output::latestFinancialStatements,
        label = "Are the latest financial statements available. (If yes, please attach any relevant document)"
    )

    val managementAccounts = selectYesOrNo(
        name = output::managementAccounts,
        label = "Are the latest management accounts available (If yes, please attach any relevant document)"
    )

    val financialsAuditedOrReviewed = selectSingle(
        name = output::financialsAuditedOrReviewed,
        label = "Have the financial statements been audited or reviewed?",
        items = listOf("Audited", "Reviewed", "Neither"),
        mapper = { Option(it, it) }
    )

    val budgetForReview = selectYesOrNo(
        name = output::budgetForReview,
        label = "Is the latest budget or financial projects available for review?"
    )

    val debtorsAging = selectYesOrNo(
        name = output::debtorsAging,
        label = "Is the latest debtors aging available? (If yes, please attach any relevant document)"
    )

    val creditAging = selectYesOrNo(
        name = output::creditAging,
        label = "Is the latest credit aging available? (If yes, please attach any relevant document)"
    )

    val longTermContracts = selectYesOrNo(
        name = output::longTermContracts,
        label = "Are there any long term contracts which could give rise to liabilities?"
    )

    val offBalanceSheetFunding = selectYesOrNo(
        name = output::offBalanceSheetFunding,
        label = "Has there been any off balance sheet funding?"
    )

    val assetRegister = selectYesOrNo(
        name = output::assetRegister,
        label = """
            Is the latest asset register available (please ensure that the following info as a minimum is captured on 
            the register name of asset, date of purchase, date of first use, write off period, method of write off,
            book value, cost of the asset, remaining useful life)?
        """.trimIndent().replace("\n"," ")
    )

    val permissionsFromLender = selectYesOrNo(
        name = output::permissionsFromLender,
        label = "Please confirm if any permissions are required from any lender prior to raising additional finance and registration/taking security."
    )

    val guarantees = text(
        name = output::guarantees,
        label = "Please confirm if the company has provided any guarantees, sureties, indemnities, letters of comfort to third parties."
    )
}