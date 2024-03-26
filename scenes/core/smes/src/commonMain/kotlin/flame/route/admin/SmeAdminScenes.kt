@file:JsExport

package flame.route.admin

import flame.route.admin.business.SmeBusinessFormScene
import flame.route.admin.contacts.SmeContactFormScene
import flame.route.admin.directors.SmeDirectorsScene
import kotlinx.JsExport

interface SmeAdminScenes {
    val business: SmeBusinessFormScene
    val contacts: SmeContactFormScene
    val directors: SmeDirectorsScene
}