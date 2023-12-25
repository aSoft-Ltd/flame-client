@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.swot

import flame.SmeApi
import flame.SmeSceneOption
import kotlinx.JsExport

class SmeSwotTheater(options: SmeSceneOption<SmeApi>) {
    val strengths by lazy { SmeStrengthsScene(options) }

    val weaknesses by lazy { SmeWeaknessesScene(options) }

    val opportunities by lazy { SmeOpportunitiesScene(options) }

    val threats by lazy { SmeThreatsScene(options) }
}