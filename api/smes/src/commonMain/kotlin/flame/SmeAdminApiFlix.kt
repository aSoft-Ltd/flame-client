package flame

import flame.admin.SmeBusinessDto
import flame.admin.SmeContactsDto
import flame.admin.SmeDirectorDto
import flame.admin.SmeLegalComplianceDto
import flame.admin.SmeShareholderDto
import kollections.List
import kollections.ListSerializer
import kotlinx.serialization.encodeToString

class SmeAdminApiFlix(options: SmeApiFlixOptions) : SmeFlixBaseApi(options), SmeAdminApi {

    override fun update(params: SmeContactsDto) = update(
        key = SmeKey.Admin.contacts,
        params = options.codec.encodeToString(SmeContactsDto.serializer(), params)
    )

    override fun update(params: SmeBusinessDto) = update(
        key = SmeKey.Admin.businesses,
        params = options.codec.encodeToString(SmeBusinessDto.serializer(), params)
    )

    override fun update(params: SmeLegalComplianceDto) = update(
        key = SmeKey.Admin.legal,
        params = options.codec.encodeToString(params)
    )

    override fun updateShareholders(params: List<SmeShareholderDto>) = update(
        key = SmeKey.Admin.shareholders,
        params = options.codec.encodeToString(ListSerializer(SmeShareholderDto.serializer()), params)
    )

    override fun updateDirectors(params: List<SmeDirectorDto>) = update(
        key = SmeKey.Admin.directors,
        params = options.codec.encodeToString(ListSerializer(SmeDirectorDto.serializer()), params)
    )
}