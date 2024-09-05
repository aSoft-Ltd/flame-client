@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.governance

import epsilon.FileOutput
import flame.SmeDto
import flame.SmeSectionProgress
import flame.forms.governance.SmeGoverningPersonnelOutput
import flame.forms.governance.SmeManPowerOutput
import flame.governance.SmeGovernanceDto
import flame.governance.SmeGoverningPersonnelDto
import flame.governance.SmeManPowerDto
import flame.governance.SmeManagementDto
import flame.transformers.utils.aggregate
import flame.transformers.utils.toCompletedIfNotEmpty
import flame.transformers.utils.toProgress
import kollections.listOf
import kollections.size
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
//    this?.insuranceScheme,
//    this?.noOfJobs,
    this?.skillShortfall,
    this?.labour,
    this?.unionised,
    this?.successPlan,
    this?.organogram,
    this?.disputes,
    this?.specialist,
).toProgress()

internal fun SmeGovernanceDto?.toProgress():SmeSectionProgress {
    val managementProgress = this?.management?.toProgress()
    val directorsProgress = this?.directors?.toProgress()
    val manPowerProgress = this?.manpower.toProgress()

    println("Directors Progress: ${directorsProgress?.info}")
    println("ManPower Progress: ${manPowerProgress?.info}")
    return listOf(
        this?.management?.toProgress() ?: SmeSectionProgress(0, 1),
        this?.directors?.toProgress() ?: SmeSectionProgress(0, 1),
        this?.manpower.toProgress()
    ).aggregate()
}

internal fun SmeManagementDto.toProgress():SmeSectionProgress {
    println("Management Progress:")
    println("Team: ${this.team.size}")
    println("Committee: ${this.committee.size}")
    val teamProgress = this.team.toCompletedIfNotEmpty()
    val commiteeProgress = this.committee.toCompletedIfNotEmpty()

    println("TeamProgress: ${teamProgress.info}")
    println("CommitteeProgress: ${commiteeProgress.info}")

    return listOf(
        this.team.toCompletedIfNotEmpty(),
        this.committee.toCompletedIfNotEmpty()
    ).aggregate()
}
