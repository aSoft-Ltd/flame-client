package flame

import koncurrent.Later

interface SmeApi : SmeScheme {
    override val admin: SmeAdminApi
    override val funding: SmeFundingApi
    val finance: SmeFinanceApi
    val documents: SmeDocumentsApi
    val governance: SmeGovernanceApi
    val swot: SmeSwotApi
    fun load(): Later<SmeDto>
}