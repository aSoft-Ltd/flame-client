package flame.transformers.reports

import flame.analysis.ReportRowDto
import kollections.map
import symphony.DynamicReportRow

internal fun DynamicReportRow.toOutput(): ReportRowDto = ReportRowDto(
    label = label.output ?: "",
    amount = total.output ?: 0.0,
    rows = rows.value.map { it.toOutput() }
)