package flame

import flame.finance.SmeBackOfficeDto
import flame.finance.SmeFinancialAcquisitionDto
import flame.finance.SmeFinancialStatusDto
import flame.funding.SmeAcquisitionDto
import flame.funding.SmeBreakdownDto
import flame.funding.SmeInvestmentDto
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch

interface SmeFinanceApi {
    fun update(params: SmeBackOfficeDto): Later<SmeDto>

    fun update(params: SmeFinancialStatusDto) : Later<SmeDto>

    fun update(params: SmeFinancialAcquisitionDto) : Later<SmeDto>
}