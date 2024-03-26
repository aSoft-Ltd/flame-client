@file:JsExport

package flame

import flame.route.admin.OwnSmeAdminScenes
import flame.route.info.OwnSmeInfoScene
import kotlinx.JsExport

class OwnSmeScenes(options: SmeSceneOption<OwnSmeScheme>) : XSmeScenes {
    override val info by lazy { OwnSmeInfoScene(options) }
    override val admin by lazy { OwnSmeAdminScenes(options) }
}