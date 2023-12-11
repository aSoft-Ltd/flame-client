package flame

import flame.governance.SmeGovernanceDto
import koncurrent.Later

interface SmeGovernanceApi : SmeFundingScheme {
    fun update(params: SmeGovernanceDto): Later<SmeDto>
}