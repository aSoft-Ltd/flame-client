package flame.transformers.finance

import flame.SmeApi
import flame.SmeSceneOption
import flame.finance.SmeFinancialAcquisitionDto
import flame.routes.SmeProgressScene
import flame.routes.financial.aquisition.SmeFinancialAcquisitionFields
import flame.routes.financial.aquisition.SmeFinancialAcquisitionOutput
import flame.transformers.toPresenter
import flame.transformers.utils.toProgress
import koncurrent.toLater
import symphony.toForm

internal fun SmeFinancialAcquisitionDto?.toOutput() = SmeFinancialAcquisitionOutput(
    statements = this?.statements,
    dd = this?.dd,
    mou = this?.mou,
)

internal fun SmeFinancialAcquisitionOutput.toParams() = SmeFinancialAcquisitionDto(
    statements = statements,
    dd = dd,
    mou = mou,
)

internal fun SmeFinancialAcquisitionDto?.toForm(options: SmeSceneOption<SmeApi>) = SmeFinancialAcquisitionFields(toOutput()).toForm(
    heading = "For Acquisitions Only",
    details = "Enter this information if you are doing acquisition",
    logger = options.logger
) {
    onSubmit { output ->
        output.toLater().then {
            it.toParams()
        }.andThen {
            options.api.finance.update(it)
        }.then {
            it.toPresenter()
        }
    }
    onSuccess {
        options.bus.dispatch(options.topic.progressMade())
    }
}

internal fun SmeFinancialAcquisitionDto?.toProgress() = listOf(
    this?.statements,
    this?.dd,
    this?.mou,
).toProgress()