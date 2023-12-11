@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.SmeSectionProgress
import flame.admin.SmeContactsDto
import flame.routes.admin.contacts.SmeContactOutput
import flame.transformers.utils.toProgress
import symphony.PhoneOutput

inline fun SmeContactsDto?.toOutput() = SmeContactOutput(
    firstName = this?.firstName,
    lastName = this?.lastName,
    email = this?.email,
    phone = PhoneOutput(this?.phone),
    role = this?.role,
    dob = this?.dob
)

inline fun SmeContactOutput.toParams() = SmeContactsDto(
    firstName = firstName,
    lastName = lastName,
    email = email,
    phone = phone?.toString(),
    role = role,
    dob = dob
)

fun SmeContactsDto?.toProgress() = listOf(
    this?.firstName,
    this?.lastName,
    this?.email,
    this?.phone,
    this?.role,
    this?.dob,
).toProgress()