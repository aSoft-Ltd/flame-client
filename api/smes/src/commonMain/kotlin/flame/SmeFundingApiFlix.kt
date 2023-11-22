package flame

import flame.funding.SmeInvestmentDto
import kotlinx.serialization.encodeToString

class SmeFundingApiFlix(options: SmeApiFlixOptions) : SmeFlixBaseApi(options), SmeFundingApi {

    override fun update(params: SmeInvestmentDto) = update(SmeKey.Funding.investment, options.codec.encodeToString(params))
}