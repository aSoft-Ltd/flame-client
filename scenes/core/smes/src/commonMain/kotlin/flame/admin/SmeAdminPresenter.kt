@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.admin

import flame.governance.SmeDirectorDto
import kollections.List
import kollections.emptyList
import kotlinx.JsExport

data class SmeAdminPresenter(
    val contacts: SmeContactsDto? = null,
    val business: SmeBusinessPresenter? = null,
    val legal: SmeLegalComplianceDto? = null,
    val directors: List<SmeDirectorDto> = emptyList(),
    val shareholders: List<SmeShareholderDto> = emptyList(),
)