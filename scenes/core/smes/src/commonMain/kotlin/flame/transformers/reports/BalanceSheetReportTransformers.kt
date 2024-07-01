package flame.transformers.reports

import flame.analysis.BalanceSheetDto
import flame.analysis.CashFlowDto
import flame.analysis.FinancialReportsDto
import flame.analysis.IncomeStatementDto
import flame.analysis.ReportDate
import flame.forms.financial.analysis.FinancialAnalysisFields
import kase.catching
import krono.LocalDate
import symphony.BalanceSheetForm
import symphony.CashFlowStatementForm
import symphony.IncomeStatementForm
import symphony.Range

internal fun BalanceSheetForm.toOutput() = BalanceSheetDto(
    assets = assets.toOutput(),
    equity = equity.toOutput(),
    liabilities = liabilities.toOutput()
)

internal fun CashFlowStatementForm.toOutput() = CashFlowDto(
    operating = operating.toOutput(),
    financing = financing.toOutput(),
    investment = investment.toOutput()
)

internal fun IncomeStatementForm.toOutput() = IncomeStatementDto(
    revenue = revenue.toOutput(),
    cogs = cogs.toOutput(),
    grossProfit = grossProfit.value,
    expenses = expenses.toOutput(),
    taxes = taxes.toOutput(),
    netProfit = 0.0,
)

private fun Range<LocalDate>?.toOutput() = catching {
    require(this != null)
    ReportDate(start, end)
}

internal fun FinancialAnalysisFields.toOutput() = catching {
    FinancialReportsDto(
        date = date.output.toOutput().getOrThrow(),
        sheet = sheet.toOutput(),
        cash = cash.toOutput(),
        income = income.toOutput()
    )
}