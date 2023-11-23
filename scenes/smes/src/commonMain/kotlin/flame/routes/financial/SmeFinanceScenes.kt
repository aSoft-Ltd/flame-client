@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.financial

import flame.SmeApi
import flame.SmeSceneOption
import flame.routes.financial.office.SmeBackOfficeFormScene
import kotlin.js.JsExport

class SmeFinanceScenes(private val options: SmeSceneOption<SmeApi>) {
    val office by lazy { SmeBackOfficeFormScene(options) }
}