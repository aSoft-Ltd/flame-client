@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.financial

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.forms.toFormScene
import flame.routes.financial.office.OwnSmeBackOfficeFormScene
import flame.transformers.finance.toFinancialStatusForm
import kotlinx.JsExport

class OwnSmeFinancialScenes(private val options: SmeSceneOption<OwnSmeScheme>) : SmeFinancialScenes {
    override val office by lazy { OwnSmeBackOfficeFormScene(options) }

    override val status by lazy {
        options.toFormScene {
            it.toFinancialStatusForm(options,"Enter the current status of your financial predicaments")
        }
    }

//    val acquisition by lazy {
//        options.toFormScene { it.finance?.acquisition.toForm(options) }
//    }
}