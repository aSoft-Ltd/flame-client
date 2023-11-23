package flame

import koncurrent.Later

interface SmeApi : SmeScheme {
    override val admin: SmeAdminApi
    override val funding: SmeFundingApi
    val finance: SmeFinanceApi

    fun load(): Later<SmeDto>
}