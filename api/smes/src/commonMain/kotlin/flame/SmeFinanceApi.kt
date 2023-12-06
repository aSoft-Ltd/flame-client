package flame

import flame.finance.SmeBackOfficeDto
import flame.finance.SmeFinancialStatusDto
import flame.funding.SmeAcquisitionDto
import flame.funding.SmeBreakdownDto
import flame.funding.SmeInvestmentDto
import koncurrent.Later

interface SmeFinanceApi : SmeFundingScheme {
    fun update(params: SmeBackOfficeDto): Later<SmeDto>

    fun update(params: SmeFinancialStatusDto) : Later<SmeDto>
}