@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import flame.routes.funding.SmeFundingScenes
import flame.route.ratios.SmeFinancialRatiosScenes
import kotlinx.JsExport

class SmeScenes(private val options: SmeSceneOption<SmeApi>) {

    val funding by lazy { SmeFundingScenes(options) }
}