package flame

import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch

interface SmeApi : SmeScheme {
    override val admin: SmeAdminApi
    override val funding: SmeFundingApi
    val finance: SmeFinanceApi
    val documents: SmeDocumentsApi
    val governance: SmeGovernanceApi
    val swot: SmeSwotApi
    fun load(): Later<SmeDto>
}