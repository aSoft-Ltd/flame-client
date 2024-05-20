@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.governance

import flame.SmeDto
import flame.forms.governance.SmeGovernanceOutput
import flame.governance.SmeGovernanceDto
import flame.transformers.utils.toProgress
import kollections.listOf


internal inline fun SmeDto.toGovernanceOutput() = governance.toOutput(this)
internal inline fun SmeGovernanceDto?.toOutput(src: SmeDto) = SmeGovernanceOutput(
    src = src,
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

internal inline fun SmeGovernanceOutput.toParams() = SmeGovernanceDto(
    insuranceScheme = insuranceScheme,
    noOfJobs = noOfJobs,
    skillShortfall = skillShortfall,
    labour = labour,
    unionised = unionised,
    successPlan = successPlan,
    organogram = organogram,
    disputes = disputes,
    specialist = specialist,
).let { src.copy(governance = it) }

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