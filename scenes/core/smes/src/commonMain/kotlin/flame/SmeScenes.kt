@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import flame.forms.toSmeScene
import flame.routes.SmeInfoScene
import flame.routes.admin.SmeAdminScenesOld
import flame.routes.documents.SmeDocumentsScenes
import flame.routes.financial.SmeFinanceScenes
import flame.routes.funding.SmeFundingScenes
import flame.routes.ratios.SmeFinancialRatios
import flame.routes.swot.SmeSwotTheater
import flame.transformers.governance.toForm
import kotlinx.JsExport

class SmeScenes(private val options: SmeSceneOption<SmeApi>) {
    val info by lazy { SmeInfoScene(options) }

    val admin by lazy { SmeAdminScenesOld(options) }

    val funding by lazy { SmeFundingScenes(options) }

    val finance by lazy { SmeFinanceScenes(options) }

    val documents by lazy { SmeDocumentsScenes(options) }

    val governance by lazy { options.toSmeScene { it.governance.toForm(options) } }

    val swot by lazy { SmeSwotTheater(options) }

    val ratios by lazy { SmeFinancialRatios(options) }
}