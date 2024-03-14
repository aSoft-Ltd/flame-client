@file:JsExport
package flame.route.admin

import flame.SmeMonitorApi
import flame.SmeSceneOption
import flame.route.admin.business.MonSmeBusinessFormScene
import kotlinx.JsExport

class MonSmeAdminScenes(options: SmeSceneOption<SmeMonitorApi>) : SmeAdminScenes2 {
    override val business by lazy { MonSmeBusinessFormScene(options) }
}