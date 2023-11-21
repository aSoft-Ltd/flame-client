@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.SmeContactsDto
import flame.routes.admin.contacts.ContactDetailsOutput
import symphony.PhoneOutput

inline fun SmeContactsDto?.toOutput() = ContactDetailsOutput(
    firstName = this?.firstName,
    lastName = this?.lastName,
    email = this?.email,
    phone = PhoneOutput(this?.phone),
    role = this?.role,
    dob = this?.dob
)

inline fun ContactDetailsOutput.toParams() = SmeContactsDto(
    firstName = firstName,
    lastName = lastName,
    email = email,
    phone = phone?.toString(),
    role = role,
    dob = dob
)