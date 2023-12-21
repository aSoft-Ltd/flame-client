package flame.transformers.governance

import flame.SmeApi
import flame.SmeSceneOption
import flame.governance.SmeGovernanceDto
import flame.routes.governance.SmeGovernanceFields
import flame.routes.governance.SmeGovernanceOutput
import flame.transformers.toPresenter
import flame.transformers.utils.toProgress
import koncurrent.toLater
import symphony.toForm
import kollections.listOf

internal fun SmeGovernanceDto?.toOutput() = SmeGovernanceOutput(
    insuranceScheme = this?.insuranceScheme,
    noOfJobs = this?.noOfJobs,
    skillShortfall = this?.skillShortfall,
    labour = this?.labour,
    unionised = this?.unionised,
    successPlan = this?.successPlan,
    organogram = this?.organogram,
    disputes = this?.disputes,
    specialist = this?.specialist,
)

internal fun SmeGovernanceOutput.toParams() = SmeGovernanceDto(
    insuranceScheme = insuranceScheme,
    noOfJobs = noOfJobs,
    skillShortfall = skillShortfall,
    labour = labour,
    unionised = unionised,
    successPlan = successPlan,
    organogram = organogram,
    disputes = disputes,
    specialist = specialist,
)

internal fun SmeGovernanceDto?.toForm(options: SmeSceneOption<SmeApi>) = SmeGovernanceFields(toOutput()).toForm(
    heading = "Manpower",
    details = "Enter company manpower information",
    logger = options.logger
) {
    onSubmit { output ->
        output.toLater().then {
            it.toParams()
        }.andThen {
            options.api.governance.update(it)
        }.then {
            it.toPresenter()
        }
    }

    onSuccess { options.bus.dispatch(options.topic.progressMade()) }
}

internal fun SmeGovernanceDto?.toProgress() = listOf(
    this?.insuranceScheme,
    this?.noOfJobs,
    this?.skillShortfall,
    this?.labour,
    this?.unionised,
    this?.successPlan,
    this?.organogram,
    this?.disputes,
    this?.specialist,
).toProgress()