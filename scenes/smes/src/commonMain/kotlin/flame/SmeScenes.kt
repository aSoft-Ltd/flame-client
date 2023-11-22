@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import flame.routes.SmeInfoScene
import flame.routes.admin.SmeAdminScenes
import flame.routes.funding.SmeFundingScenes
import kotlin.js.JsExport

class SmeScenes(private val options: SmeSceneOption<SmeApi>) {
    val info by lazy { SmeInfoScene(options) }

    val admin by lazy { SmeAdminScenes(options) }

    val funding by lazy { SmeFundingScenes(options) }
}