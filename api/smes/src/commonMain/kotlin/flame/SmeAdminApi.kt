package flame

import flame.admin.SmeBusinessDto
import flame.admin.SmeContactsDto
import flame.admin.SmeDirectorDto
import flame.admin.SmeLegalComplianceDto
import flame.admin.SmeShareholderDto
import koncurrent.Later
import kollections.List

interface SmeAdminApi : SmeAdminScheme {
    fun update(params: SmeContactsDto): Later<SmeDto>

    fun update(params: SmeBusinessDto) : Later<SmeDto>

    fun update(params: SmeLegalComplianceDto) : Later<SmeDto>

    fun updateShareholders(params: List<SmeShareholderDto>) : Later<SmeDto>

    fun updateDirectors(params: List<SmeDirectorDto>) : Later<SmeDto>
}