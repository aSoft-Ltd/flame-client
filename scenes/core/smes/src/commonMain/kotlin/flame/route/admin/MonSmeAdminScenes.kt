@file:JsExport
package flame.route.admin

import flame.MonSmeScheme
import flame.SmeSceneOption
import flame.route.admin.business.MonSmeBusinessFormScene
import flame.route.admin.contacts.MonSmeContactFormScene
import flame.route.admin.directors.MonSmeDirectorsScene
import kotlinx.JsExport

class MonSmeAdminScenes(options: SmeSceneOption<MonSmeScheme>) : SmeAdminScenes {
    override val business by lazy { MonSmeBusinessFormScene(options) }
    override val contacts by lazy { MonSmeContactFormScene(options) }
    override val directors by lazy { MonSmeDirectorsScene(options) }
}