@file:JsExport

package flame.routes.admin

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.routes.admin.business.OwnSmeBusinessFormScene
import flame.routes.admin.contacts.OwnSmeContactFormScene
import flame.routes.admin.directors.OwnSmeDirectorsScene
import flame.routes.admin.legal.OwnSmeLegalFormScene
import flame.routes.admin.shareholders.OwnSmeShareholderScene
import kotlinx.JsExport

class OwnSmeAdminScenes(options: SmeSceneOption<OwnSmeScheme>) : SmeAdminScenes {
    override val business by lazy { OwnSmeBusinessFormScene(options) }
    override val contacts by lazy { OwnSmeContactFormScene(options) }
    override val directors by lazy { OwnSmeDirectorsScene(options) }
    override val shareholders by lazy { OwnSmeShareholderScene(options) }
    override val legal by lazy { OwnSmeLegalFormScene(options) }
}