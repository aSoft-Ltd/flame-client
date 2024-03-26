@file:JsExport

package flame.route.admin

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.route.admin.business.OwnSmeBusinessFormScene
import flame.route.admin.contacts.OwnSmeContactFormScene
import flame.route.admin.directors.OwnSmeDirectorsScene
import flame.route.admin.legal.OwnSmeLegalFormScene
import flame.route.admin.shareholders.OwnSmeShareholderScene
import flame.route.admin.shareholders.SmeShareholderScene
import kotlinx.JsExport

class OwnSmeAdminScenes(options: SmeSceneOption<OwnSmeScheme>) : SmeAdminScenes {
    override val business by lazy { OwnSmeBusinessFormScene(options) }
    override val contacts by lazy { OwnSmeContactFormScene(options) }
    override val directors by lazy { OwnSmeDirectorsScene(options) }
    override val shareholders by lazy { OwnSmeShareholderScene(options) }
    override val legal by lazy { OwnSmeLegalFormScene(options) }
}