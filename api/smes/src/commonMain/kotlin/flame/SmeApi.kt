package flame

import koncurrent.Later

interface SmeApi : SmeScheme {
    override val admin: SmeAdminApi

    fun load(): Later<SmeDto>
}