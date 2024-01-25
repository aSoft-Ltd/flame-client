package flame

import flame.governance.SmeGovernanceDto
import kotlinx.serialization.encodeToString

class SmeGovernanceApiFlix(options: SmeApiFlixOptions) : SmeFlixBaseApi(options), SmeGovernanceScheme {

    override fun update(params: SmeGovernanceDto) = update(SmeKey.Governance,options.codec.encodeToString(params))
}