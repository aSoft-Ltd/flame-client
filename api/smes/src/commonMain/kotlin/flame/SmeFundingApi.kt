package flame

import flame.funding.SmeAcquisitionDto
import flame.funding.SmeBreakdownDto
import flame.funding.SmeInvestmentDto
import koncurrent.Later

interface SmeFundingApi : SmeFundingScheme {
    fun update(params: SmeInvestmentDto): Later<SmeDto>

    fun update(params: SmeBreakdownDto): Later<SmeDto>

    fun update(params: SmeAcquisitionDto) : Later<SmeDto>
}