package flame

import flame.finance.SmeBackOfficeDto
import flame.finance.SmeFinancialAcquisitionDto
import flame.finance.SmeFinancialStatusDto
import flame.funding.SmeAcquisitionDto
import flame.funding.SmeBreakdownDto
import flame.funding.SmeInvestmentDto
import koncurrent.Later
import kotlinx.serialization.encodeToString

class SmeFinanceApiFlix(options: SmeApiFlixOptions) : SmeFlixBaseApi(options), SmeFinanceApi {

    override fun update(params: SmeBackOfficeDto) = update(SmeKey.Finance.office, options.codec.encodeToString(params))

    override fun update(params: SmeFinancialStatusDto) = update(SmeKey.Finance.status, options.codec.encodeToString(params))

    override fun update(params: SmeFinancialAcquisitionDto) = update(SmeKey.Finance.acquisition, options.codec.encodeToString(params))
}