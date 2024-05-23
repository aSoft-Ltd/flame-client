@file:JsExport

package flame.routes.plan

import flame.OwnSmeApi
import flame.SmeSceneOptions
import flame.routes.plan.marketing.OwnSmeMarketPlanScene
import kotlinx.JsExport

class OwnSmeBusinessPlanTheater(options: SmeSceneOptions<OwnSmeApi>) : SmeBusinessPlanTheater {
    override val marketing by lazy { OwnSmeMarketPlanScene(options) }
}