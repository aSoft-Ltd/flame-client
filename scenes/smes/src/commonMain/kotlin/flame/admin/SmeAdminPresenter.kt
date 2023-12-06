@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.admin

import kollections.List
import kollections.iEmptyList
import kotlin.js.JsExport

data class SmeAdminPresenter(
    val contacts: SmeContactsDto? = null,
    val business: SmeBusinessPresenter? = null,
    val legal: SmeLegalComplianceDto? = null,
    val directors: List<SmeDirectorDto> = iEmptyList(),
    val shareholders: List<SmeShareholderDto> = iEmptyList(),
)