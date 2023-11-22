package flame

import koncurrent.Later

interface SmeAdminApi : SmeAdminScheme {
    fun update(params: SmeContactsDto): Later<SmeDto>

    fun update(params: SmeBusinessDto) : Later<SmeDto>

    fun update(params: SmeLegalComplianceDto) : Later<SmeDto>

    fun updateShareholders(params: List<SmeShareholderDto>) : Later<SmeDto>

    fun updateDirectors(params: List<SmeDirectorDto>) : Later<SmeDto>
}