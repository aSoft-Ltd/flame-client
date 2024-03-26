package flame.transformers.finance

import flame.SmeDto
import flame.finance.SmeFinancialAcquisitionDto
import flame.forms.financial.aquisition.SmeFinancialAcquisitionOutput
import flame.transformers.utils.toProgress
import kollections.listOf

internal fun SmeFinancialAcquisitionDto?.toOutput(src: SmeDto) = SmeFinancialAcquisitionOutput(
    src = src,
    statements = this?.statements,
    dd = this?.dd,
    mou = this?.mou,
)

internal fun SmeFinancialAcquisitionOutput.toParams() = SmeFinancialAcquisitionDto(
    statements = statements,
    dd = dd,
    mou = mou,
)

internal fun SmeFinancialAcquisitionDto?.toProgress() = listOf(
    this?.statements,
    this?.dd,
    this?.mou,
).toProgress()