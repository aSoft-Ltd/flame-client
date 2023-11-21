@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin

import flame.SmeApi
import flame.SmeSceneOption
import flame.routes.admin.business.BusinessDetailsFormScene
import flame.routes.admin.contacts.ContactDetailsFormScene
import kotlin.js.JsExport
import lexi.LoggerFactory

class AdminScenes(private val options: SmeSceneOption<SmeApi>) {
    val business by lazy { BusinessDetailsFormScene(options) }

    val contacts by lazy { ContactDetailsFormScene(options) }
}