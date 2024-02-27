@file:JsExport

package flame

import flame.route.admin.MonSmeAdminScenes
import flame.route.info.MonSmeInfoScene
import kotlinx.JsExport

class MonBusinessesScenes(options: SmeSceneOption<SmeMonitorApi>) : SmeBusinessScene {
    override val info by lazy { MonSmeInfoScene(options) }
    override val admin by lazy { MonSmeAdminScenes(options) }
}