package flame

import flame.funding.SmeAcquisitionDto
import flame.funding.SmeBreakdownDto
import flame.funding.SmeInvestmentDto
import koncurrent.Later
import kotlinx.serialization.encodeToString

class SmeFundingApiFlix(options: SmeApiFlixOptions) : SmeFlixBaseApi(options), SmeFundingApi {

    override fun update(params: SmeInvestmentDto) = update(SmeKey.Funding.investment, options.codec.encodeToString(params))

    override fun update(params: SmeBreakdownDto) = update(SmeKey.Funding.breakdown, options.codec.encodeToString(params))

    override fun update(params: SmeAcquisitionDto) = update(SmeKey.Funding.acquisition, options.codec.encodeToString(params))
}