@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.SmeBusinessDto
import flame.SmeContactsDto
import flame.routes.admin.business.SmeBusinessOutput
import flame.routes.admin.contacts.SmeContactOutput
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