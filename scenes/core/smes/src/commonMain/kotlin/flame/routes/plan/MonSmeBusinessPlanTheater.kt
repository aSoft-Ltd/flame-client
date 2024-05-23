@file:JsExport

package flame.routes.plan

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.marketing.MonSmeMarketPlanScene
import kotlinx.JsExport

class MonSmeBusinessPlanTheater(options: SmeSceneOptions<MonSmeScheme>) : SmeBusinessPlanTheater {
    override val marketing by lazy { MonSmeMarketPlanScene(options) }
}