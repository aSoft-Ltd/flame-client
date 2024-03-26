@file:JsExport

package flame

import flame.route.admin.OwnSmeAdminScenes
import flame.route.documents.OwnSmeDocumentsScenes
import flame.route.documents.SmeDocumentsScenes
import flame.route.info.OwnSmeInfoScene
import kotlinx.JsExport

class OwnSmeScenes(options: SmeSceneOption<OwnSmeScheme>) : XSmeScenes {
    override val info by lazy { OwnSmeInfoScene(options) }
    override val admin by lazy { OwnSmeAdminScenes(options) }
    override val documents by lazy { OwnSmeDocumentsScenes(options) }
}