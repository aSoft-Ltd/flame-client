@file:JsExport

package flame.route.admin

import flame.SmeApi
import flame.SmeSceneOption
import flame.route.admin.business.OwnSmeBusinessFormScene
import kotlinx.JsExport

class OwnSmeAdminScenes(options: SmeSceneOption<SmeApi>) : SmeAdminScenes2 {
    override val business by lazy { OwnSmeBusinessFormScene(options) }
}