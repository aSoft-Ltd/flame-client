package flame.transformers.finance

import flame.SmeApi
import flame.SmeDto
import flame.SmeSceneOption
import flame.finance.SmeFinancialAcquisitionDto
import flame.forms.financial.aquisition.SmeFinancialAcquisitionFields
import flame.forms.financial.aquisition.SmeFinancialAcquisitionOutput
import flame.transformers.toPresenter
import flame.transformers.utils.toProgress
import kollections.listOf
import koncurrent.toLater
import koncurrent.later.then
import koncurrent.later.andThen
import symphony.toForm

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