@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.SmeDto
import flame.admin.SmeAdminDto
import kollections.List
import flame.governance.SmeDirectorDto
import flame.forms.admin.directors.SmeDirectorOutput
import symphony.PhoneOutput

internal inline fun SmeDto.copy(directors: List<SmeDirectorDto>) = copy(
//    admin = (admin ?: SmeAdminDto()).copy(directors = directors)
)


internal inline fun SmeDirectorDto?.toOutput() = SmeDirectorOutput(
    name = this?.name,
    email = this?.email,
    phone = PhoneOutput(this?.phone),
)

internal inline fun SmeDirectorOutput.toParams() = SmeDirectorDto(
    name = name,
    email = email,
    phone = phone?.toString()
)