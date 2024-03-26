@file:JsExport

package flame

import flame.route.admin.SmeAdminScenes
import flame.route.info.SmeInfoScene2
import kotlinx.JsExport

interface XSmeScenes {
    val admin: SmeAdminScenes
    val info: SmeInfoScene2
}