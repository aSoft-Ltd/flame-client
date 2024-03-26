@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin

import flame.SmeApi
import flame.SmeSceneOption
import flame.routes.admin.legal.SmeLegalFormScene
import flame.routes.admin.shareholders.SmeShareholderScene
import kotlinx.JsExport

class SmeAdminScenesOld(private val options: SmeSceneOption<SmeApi>) {

    val legal by lazy { SmeLegalFormScene(options) }

    val shareholders by lazy { SmeShareholderScene(options) }
}