@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin

import flame.routes.admin.business.BusinessDetailsFormScene
import kotlin.js.JsExport
import lexi.LoggerFactory

class AdminScenes(private val logger: LoggerFactory) {
    val business by lazy { BusinessDetailsFormScene(logger) }

    val contacts by lazy { BusinessDetailsFormScene(logger) }
}