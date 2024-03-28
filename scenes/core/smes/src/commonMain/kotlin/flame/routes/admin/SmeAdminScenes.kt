@file:JsExport

package flame.routes.admin

import flame.routes.admin.business.SmeBusinessFormScene
import flame.routes.admin.contacts.SmeContactFormScene
import flame.routes.admin.directors.SmeDirectorsScene
import flame.routes.admin.legal.SmeLegalFormScene
import flame.routes.admin.shareholders.SmeShareholderScene
import kotlinx.JsExport

sealed interface SmeAdminScenes {
    val business: SmeBusinessFormScene
    val contacts: SmeContactFormScene
    val directors: SmeDirectorsScene
    val shareholders: SmeShareholderScene
    val legal: SmeLegalFormScene
}