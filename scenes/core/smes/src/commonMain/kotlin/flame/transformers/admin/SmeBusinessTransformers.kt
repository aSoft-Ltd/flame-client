@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.SmeDto
import flame.admin.SmeAdminDto
import flame.admin.SmeBusinessDto
import flame.admin.SmeBusinessPresenter
import flame.forms.admin.business.SmeBusinessOutput
import flame.transformers.utils.toProgress
import geo.toPresenter
import geo.transformers.toOutput
import symphony.PhoneOutput
import kollections.listOf

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

fun SmeBusinessDto?.toProgress() = listOf(
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
).toProgress()

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

inline fun SmeDto.copy(business: SmeBusinessDto) = copy(
    admin = (admin ?: SmeAdminDto()).copy(business = business)
)