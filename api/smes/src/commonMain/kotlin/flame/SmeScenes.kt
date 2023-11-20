@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import flame.routes.admin.AdminScenes
import kotlin.js.JsExport
import lexi.LoggerFactory

class SmeScenes(private val logger: LoggerFactory) {
    val admin by lazy {
        AdminScenes(logger)
    }
}