@file:JsExport

package flame.forms.financial.analysis

import kotlinx.JsExport
import krono.range
import symphony.BalanceSheetForm
import symphony.CashFlowStatementForm
import symphony.Fields
import symphony.IncomeStatementForm

class FinancialAnalysisFields : Fields<FinancialAnalysisOutput>(FinancialAnalysisOutput(null)) {
    val date = range(name = output::date)
    val sheet = BalanceSheetForm()
    val cash = CashFlowStatementForm()
    val income = IncomeStatementForm()
}