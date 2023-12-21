@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.admin

import kollections.List
import kollections.emptyList
import kotlin.js.JsExport

data class SmeAdminPresenter(
    val contacts: SmeContactsDto? = null,
    val business: SmeBusinessPresenter? = null,
    val legal: SmeLegalComplianceDto? = null,
    val directors: List<SmeDirectorDto> = emptyList(),
    val shareholders: List<SmeShareholderDto> = emptyList(),
)