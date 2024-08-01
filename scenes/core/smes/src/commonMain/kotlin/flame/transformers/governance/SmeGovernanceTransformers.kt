@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.governance

import epsilon.FileOutput
import flame.SmeDto
import flame.forms.governance.SmeGoverningPersonnelOutput
import flame.forms.governance.SmeManPowerOutput
import flame.governance.SmeGovernanceDto
import flame.governance.SmeGoverningPersonnelDto
import flame.governance.SmeManPowerDto
import flame.transformers.utils.toProgress
import kollections.listOf
import koncurrent.toLater


internal inline fun SmeDto.toManPowerOutput() = governance?.manpower.toOutput(this)

internal inline fun SmeManPowerDto?.toOutput(src: SmeDto) = SmeManPowerOutput(
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

internal inline fun SmeGoverningPersonnelDto?.toOutput() = SmeGoverningPersonnelOutput(
    src = this,
    name = this?.name,
    role = this?.role,
    qualification = this?.qualification,
    fieldOfStudy = this?.fieldOfStudy,
    experience = this?.experience,
    resume = FileOutput(url = this?.resume?.url)
)

internal inline fun SmeGoverningPersonnelOutput.toParams() = SmeGoverningPersonnelDto(
    name, role, qualification, fieldOfStudy, experience, src?.resume
)

internal inline fun SmeManPowerOutput.toParams() = SmeManPowerDto(
    insuranceScheme = insuranceScheme,
    noOfJobs = noOfJobs,
    skillShortfall = skillShortfall,
    labour = labour,
    unionised = unionised,
    successPlan = successPlan,
    organogram = organogram,
    disputes = disputes,
    specialist = specialist,
).let {
    val governance = src.governance ?: SmeGovernanceDto()
    src.copy(governance = governance.copy(manpower = it))
}

internal fun SmeManPowerDto?.toProgress() = listOf(
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