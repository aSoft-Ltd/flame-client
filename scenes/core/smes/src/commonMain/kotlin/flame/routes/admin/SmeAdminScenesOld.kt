@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin

import flame.SmeApi
import flame.SmeSceneOption
import flame.route.admin.legal.SmeLegalFormScene
import kotlinx.JsExport

class SmeAdminScenesOld(private val options: SmeSceneOption<SmeApi>) {

    val legal by lazy { SmeLegalFormScene(options) }
}