@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.SmeDto
import flame.admin.SmeAdminDto
import flame.admin.SmeContactsDto
import flame.forms.admin.contacts.SmeContactOutput
import flame.transformers.utils.toProgress
import symphony.PhoneOutput
import kollections.listOf

internal inline fun SmeDto.toContactsOutput() = admin?.contacts.toOutput(this)
internal inline fun SmeContactsDto?.toOutput(src: SmeDto) = SmeContactOutput(
    src = src,
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
).let {
    src.copy(admin = (src.admin ?: SmeAdminDto()).copy(contacts = it))
}

fun SmeContactsDto?.toProgress() = listOf(
    this?.firstName,
    this?.lastName,
    this?.email,
    this?.phone,
    this?.role,
    this?.dob,
).toProgress()