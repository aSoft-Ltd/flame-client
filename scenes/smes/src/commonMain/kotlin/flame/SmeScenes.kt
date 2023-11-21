@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import flame.routes.admin.AdminScenes
import kotlin.js.JsExport

class SmeScenes(private val options: SmeSceneOption<SmeApi>) {
    val admin by lazy {
        AdminScenes(options)
    }
}