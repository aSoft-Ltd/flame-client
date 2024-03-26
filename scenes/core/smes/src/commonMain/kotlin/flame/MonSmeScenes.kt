@file:JsExport

package flame

import flame.route.admin.MonSmeAdminScenes
import flame.route.documents.MonSmeDocumentsScenes
import flame.route.documents.OwnSmeDocumentsScenes
import flame.route.info.MonSmeInfoScene
import kotlinx.JsExport

class MonSmeScenes(options: SmeSceneOption<MonSmeScheme>) : XSmeScenes {
    override val info by lazy { MonSmeInfoScene(options) }
    override val admin by lazy { MonSmeAdminScenes(options) }
    override val documents by lazy { MonSmeDocumentsScenes(options) }
}