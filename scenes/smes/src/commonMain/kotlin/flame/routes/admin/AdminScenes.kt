@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin

import flame.SmeApi
import flame.SmeSceneOption
import flame.routes.admin.business.SmeBusinessFormScene
import flame.routes.admin.contacts.SmeContactFormScene
import flame.routes.admin.directors.SmeDirectorsScene
import flame.routes.admin.legal.SmeLegalFormScene
import flame.routes.admin.shareholders.SmeShareholderScene
import kotlin.js.JsExport

class AdminScenes(private val options: SmeSceneOption<SmeApi>) {
    val business by lazy { SmeBusinessFormScene(options) }

    val contacts by lazy { SmeContactFormScene(options) }

    val legal by lazy { SmeLegalFormScene(options) }

    val directors by lazy { SmeDirectorsScene(options) }

    val shareholders by lazy { SmeShareholderScene(options) }
}