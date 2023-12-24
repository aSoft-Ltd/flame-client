package flame

import flame.admin.SmeBusinessDto
import flame.admin.SmeContactsDto
import flame.admin.SmeDirectorDto
import flame.admin.SmeLegalComplianceDto
import flame.admin.SmeShareholderDto
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import kollections.List

interface SmeAdminApi : SmeAdminScheme {
    fun update(params: SmeContactsDto): Later<SmeDto>

    fun update(params: SmeBusinessDto) : Later<SmeDto>

    fun update(params: SmeLegalComplianceDto) : Later<SmeDto>

    fun updateShareholders(params: List<SmeShareholderDto>) : Later<SmeDto>

    fun updateDirectors(params: List<SmeDirectorDto>) : Later<SmeDto>
}