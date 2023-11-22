package flame

import flame.funding.SmeInvestmentDto
import koncurrent.Later

interface SmeFundingApi : SmeFundingScheme {
    fun update(params: SmeInvestmentDto): Later<SmeDto>
}