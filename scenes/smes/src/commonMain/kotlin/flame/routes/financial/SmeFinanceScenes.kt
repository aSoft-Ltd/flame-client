@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.financial

import flame.SmeApi
import flame.SmeSceneOption
import flame.routes.financial.office.SmeBackOfficeFormScene
import flame.transformers.finance.toForm
import flame.utils.toSmeScene
import kotlin.js.JsExport

class SmeFinanceScenes(private val options: SmeSceneOption<SmeApi>) {
    val office by lazy { SmeBackOfficeFormScene(options) }

    val status by lazy { options.toSmeScene { it.finance?.status.toForm(options) } }

    val acquisition by lazy { options.toSmeScene { it.finance?.acquisition.toForm(options) } }
}