@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.route.financial

import flame.MonSmeScheme
import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.forms.toFormScene
import flame.route.financial.office.MonSmeBackOfficeFormScene
import flame.route.financial.office.OwnSmeBackOfficeFormScene
import flame.transformers.finance.toFinancialStatusForm
import kotlinx.JsExport

class MonSmeFinancialScenes(private val options: SmeSceneOption<MonSmeScheme>) : SmeFinancialScenes {
    override val office by lazy { MonSmeBackOfficeFormScene(options) }

    override val status by lazy {
        options.toFormScene {
            val label = it.admin?.business?.name ?: "SME"
            it.toFinancialStatusForm(options,"Enter the current financial status predicaments for $label")
        }
    }

//    val acquisition by lazy {
//        options.toFormScene { it.finance?.acquisition.toForm(options) }
//    }
}