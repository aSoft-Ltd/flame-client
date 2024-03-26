@file:JsExport

package flame.route.admin

import flame.route.admin.business.SmeBusinessFormScene
import flame.route.admin.contacts.SmeContactFormScene
import flame.route.admin.directors.SmeDirectorsScene
import flame.route.admin.legal.SmeLegalFormScene
import flame.route.admin.shareholders.SmeShareholderScene
import kotlinx.JsExport

sealed interface SmeAdminScenes {
    val business: SmeBusinessFormScene
    val contacts: SmeContactFormScene
    val directors: SmeDirectorsScene
    val shareholders: SmeShareholderScene
    val legal: SmeLegalFormScene
}