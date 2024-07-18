package flame.transformers.reports

import flame.analysis.BalanceSheetDto
import flame.analysis.CashFlowDto
import flame.analysis.FinancialReportsDto
import flame.analysis.IncomeStatementDto
import flame.analysis.ReportRowDto
import kollections.List
import kollections.contains
import kollections.filter
import kollections.forEach
import kollections.isEmpty
import kollections.map
import kollections.plus
import kollections.toMutableList

internal fun List<FinancialReportsDto>.merged(): List<FinancialReportsDto> {
    var sheet: BalanceSheetDto? = null
    var cash: CashFlowDto? = null
    var income: IncomeStatementDto? = null

    forEach {
        sheet = sheet?.merged(it.sheet)
    }
    TODO()
}

private fun BalanceSheetDto?.merged(other: BalanceSheetDto) = BalanceSheetDto(
    assets = this?.assets.merged(other.assets),
    equity = this?.equity.merged(other.equity),
    liabilities = this?.liabilities.merged(other.liabilities)
)

private fun ReportRowDto?.merged(that: ReportRowDto): ReportRowDto {
    if(this == null) return that
    if(that.rows.isEmpty()) return this
    if(this.rows.isEmpty()) return that

    val left = rows.toMutableList()
    val existing = left.map { it.label }
    val right = that.rows.toMutableList().filter { !existing.contains(it.label) }
    val rows = left + right

//    return ReportRowDto(label, amount, rows.merged())
    return ReportRowDto(label, amount, rows)
}

private fun List<ReportRowDto>.merged() : List<ReportRowDto> {
    throw Exception("Not using FinancialReportRows")
}