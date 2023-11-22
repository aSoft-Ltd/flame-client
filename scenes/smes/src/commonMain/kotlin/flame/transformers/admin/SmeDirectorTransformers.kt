@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.SmeDirectorDto
import flame.routes.admin.directors.SmeDirectorOutput
import symphony.PhoneOutput

inline fun SmeDirectorDto?.toOutput() = SmeDirectorOutput(
    name = this?.name,
    email = this?.email,
    phone = PhoneOutput(this?.phone),
)

inline fun SmeDirectorOutput.toParams() = SmeDirectorDto(
    name = name,
    email = email,
    phone = phone?.toString()
)