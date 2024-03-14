@file:JsExport

package flame

import flame.route.admin.SmeAdminScenes2
import flame.route.info.SmeInfoScene2
import kotlinx.JsExport

interface SmeBusinessScene {
    val admin: SmeAdminScenes2
    val info: SmeInfoScene2
}