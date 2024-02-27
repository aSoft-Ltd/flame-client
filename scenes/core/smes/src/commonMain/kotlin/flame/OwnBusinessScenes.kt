@file:JsExport

package flame

import flame.route.admin.OwnSmeAdminScenes
import flame.route.info.OwnSmeInfoScene
import kotlinx.JsExport

class OwnBusinessScenes(options: SmeSceneOption<SmeApi>) : SmeBusinessScene {
    override val info by lazy { OwnSmeInfoScene(options) }
    override val admin by lazy { OwnSmeAdminScenes(options) }
}