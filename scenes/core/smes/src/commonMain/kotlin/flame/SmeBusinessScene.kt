@file:JsExport

package flame

import flame.route.admin.SmeAdminScenes
import flame.route.info.SmeInfoScene
import kotlinx.JsExport

interface SmeBusinessScene {
    val admin: SmeAdminScenes
    val info: SmeInfoScene
}