@file:JsExport

package flame.route.admin

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.route.admin.business.OwnSmeBusinessFormScene
import flame.route.admin.contacts.OwnSmeContactFormScene
import flame.route.admin.directors.OwnSmeDirectorsScene
import kotlinx.JsExport

class OwnSmeAdminScenes(options: SmeSceneOption<OwnSmeScheme>) : SmeAdminScenes {
    override val business by lazy { OwnSmeBusinessFormScene(options) }
    override val contacts by lazy { OwnSmeContactFormScene(options) }
    override val directors by lazy { OwnSmeDirectorsScene(options) }
}