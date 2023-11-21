package flame

import koncurrent.Later

interface SmeAdminApi : SmeAdminScheme {
    fun update(params: SmeContactsDto): Later<SmeDto>
}