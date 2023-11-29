@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import flame.routes.SmeInfoScene
import flame.routes.admin.SmeAdminScenes
import flame.routes.documents.SmeDocumentsScenes
import flame.routes.financial.SmeFinanceScenes
import flame.routes.funding.SmeFundingScenes
import kotlin.js.JsExport

class SmeScenes(private val options: SmeSceneOption<SmeApi>) {
    val info by lazy { SmeInfoScene(options) }

    val admin by lazy { SmeAdminScenes(options) }

    val funding by lazy { SmeFundingScenes(options) }

    val finance by lazy { SmeFinanceScenes(options) }

    val documents by lazy { SmeDocumentsScenes(options) }
}