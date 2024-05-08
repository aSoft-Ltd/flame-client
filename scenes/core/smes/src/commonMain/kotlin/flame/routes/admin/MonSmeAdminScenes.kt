@file:JsExport
package flame.routes.admin

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.routes.admin.business.MonSmeBusinessFormScene
import flame.routes.admin.contacts.MonSmeContactFormScene
import flame.routes.admin.directors.MonSmeDirectorsScene
import flame.routes.admin.legal.MonSmeLegalFormScene
import flame.routes.admin.shareholders.MonSmeShareholderScene
import kotlinx.JsExport

class MonSmeAdminScenes(options: SmeSceneOptions<MonSmeScheme>) : SmeAdminScenes {
    override val business by lazy { MonSmeBusinessFormScene(options) }
    override val contacts by lazy { MonSmeContactFormScene(options) }
    override val directors by lazy { MonSmeDirectorsScene(options) }
    override val shareholders by lazy { MonSmeShareholderScene(options) }
    override val legal by lazy { MonSmeLegalFormScene(options) }
}