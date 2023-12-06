@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.SmeSectionProgress
import flame.admin.SmeBusinessDto
import flame.admin.SmeBusinessPresenter
import flame.routes.admin.business.SmeBusinessOutput
import geo.toPresenter
import geo.transformers.toOutput
import symphony.PhoneOutput

inline fun SmeBusinessDto?.toOutput() = SmeBusinessOutput(
    name = this?.name,
    registration = this?.registration,
    phone = PhoneOutput(this?.phone),
    yearsInOperation = this?.yearsInOperation,
    address = this?.address.toOutput(),
    numberOfJobs = this?.numberOfJobs,
    industry = this?.industry,
    businessStage = this?.businessStage,
    bbbee = this?.bbbee,
    staffComplement = this?.staffComplement,
    description = this?.description,
)

inline fun SmeBusinessOutput.toParams() = SmeBusinessDto(
    name = name,
    registration = registration,
    phone = phone?.toString(),
    yearsInOperation = yearsInOperation,
    address = address?.toDto()?.getOrNull(),
    numberOfJobs = numberOfJobs,
    industry = industry,
    businessStage = businessStage,
    bbbee = bbbee,
    staffComplement = staffComplement,
    description = description,
)

fun SmeBusinessDto?.toProgress(): SmeSectionProgress {
    val total = listOf(
        this?.name,
        this?.registration,
        this?.phone,
        this?.yearsInOperation,
        this?.address,
        this?.numberOfJobs,
        this?.industry,
        this?.businessStage,
        this?.bbbee,
        this?.staffComplement,
        this?.description
    )
    val completed = total.filterNotNull()
    return SmeSectionProgress(completed.size, total.size)
}

inline fun SmeBusinessPresenter?.toProgress() = this?.src?.toPresenter()

fun SmeBusinessDto.toPresenter() = SmeBusinessPresenter(
    src = this,
    name = name,
    registration = registration,
    phone = phone,
    yearsInOperation = yearsInOperation,
    address = address?.toPresenter(),
    numberOfJobs = numberOfJobs,
    industry = industry,
    businessStage = businessStage,
    bbbee = bbbee,
    staffComplement = staffComplement,
    description = description,
)