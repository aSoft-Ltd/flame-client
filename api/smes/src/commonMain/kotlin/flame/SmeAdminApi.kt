package flame

import koncurrent.Later

interface SmeAdminApi : SmeAdminScheme {
    fun update(params: SmeContactsDto): Later<SmeDto>
    fun update(params: SmeBusinessDto) : Later<SmeDto>
    fun update(params: SmeLegalComplianceDto) : Later<SmeDto>

    fun update(params: List<SmeShareholderDto>) : Later<SmeDto>
}