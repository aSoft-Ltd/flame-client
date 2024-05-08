@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.financial

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.forms.toFormScene
import flame.routes.financial.office.MonSmeBackOfficeFormScene
import flame.transformers.finance.toFinancialStatusForm
import kotlinx.JsExport

class MonSmeFinancialScenes(private val options: SmeSceneOptions<MonSmeScheme>) : SmeFinancialScenes {
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