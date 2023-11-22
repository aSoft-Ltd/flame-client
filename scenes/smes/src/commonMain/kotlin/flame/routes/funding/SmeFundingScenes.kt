@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.funding

import flame.SmeApi
import flame.SmeSceneOption
import flame.routes.funding.acquisition.SmeAcquisitionFormScene
import flame.routes.funding.breakdown.SmeBreakdownFormScene
import flame.routes.funding.investments.SmeInvestmentFormScene
import kotlin.js.JsExport

class SmeFundingScenes(private val options: SmeSceneOption<SmeApi>) {

    val investments by lazy { SmeInvestmentFormScene(options) }

    val breakdown by lazy { SmeBreakdownFormScene(options) }

    val acquisition by lazy { SmeAcquisitionFormScene(options) }
}