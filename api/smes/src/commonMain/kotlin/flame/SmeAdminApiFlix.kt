package flame

import flame.admin.SmeBusinessDto
import flame.admin.SmeContactsDto
import flame.admin.SmeDirectorDto
import flame.admin.SmeLegalComplianceDto
import flame.admin.SmeShareholderDto
import kotlinx.serialization.encodeToString

class SmeAdminApiFlix(options: SmeApiFlixOptions) : SmeFlixBaseApi(options), SmeAdminApi {

    override fun update(params: SmeContactsDto) = update(SmeKey.Admin.contacts, options.codec.encodeToString(params))

    override fun update(params: SmeBusinessDto) = update(SmeKey.Admin.businesses, options.codec.encodeToString(params))

    override fun update(params: SmeLegalComplianceDto) = update(SmeKey.Admin.legal, options.codec.encodeToString(params))

    override fun updateShareholders(params: List<SmeShareholderDto>) = update(SmeKey.Admin.shareholders, options.codec.encodeToString(params))

    override fun updateDirectors(params: List<SmeDirectorDto>) = update(SmeKey.Admin.directors, options.codec.encodeToString(params))
}