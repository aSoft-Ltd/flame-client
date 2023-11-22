@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin

import flame.SmeApi
import flame.SmeSceneOption
import flame.routes.admin.business.SmeBusinessFormScene
import flame.routes.admin.contacts.SmeContactFormScene
import kotlin.js.JsExport

class AdminScenes(private val options: SmeSceneOption<SmeApi>) {
    val business by lazy { SmeBusinessFormScene(options) }

    val contacts by lazy { SmeContactFormScene(options) }

    val legal by lazy {

    }
}