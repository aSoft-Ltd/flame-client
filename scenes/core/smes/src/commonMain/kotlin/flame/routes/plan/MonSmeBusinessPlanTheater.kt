@file:JsExport

package flame.routes.plan

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.competitors.MonSmeCompetitorsPlanScene
import flame.routes.plan.customers.MonSmeCustomerPlanScene
import flame.routes.plan.estate.MonSmeEstatePlanScene
import flame.routes.plan.industry.MonSmeIndustryPlanScene
import flame.routes.plan.legal.MonSmeLegalPlanScene
import flame.routes.plan.marketing.MonSmeMarketPlanScene
import flame.routes.plan.services.MonSmeServicesPlanScene
import flame.routes.plan.suppliers.MonSmeSupplierPlanScene
import kotlinx.JsExport

class MonSmeBusinessPlanTheater(options: SmeSceneOptions<MonSmeScheme>) : SmeBusinessPlanTheater {
    override val marketing by lazy { MonSmeMarketPlanScene(options) }
    override val services by lazy { MonSmeServicesPlanScene(options) }
    override val industry by lazy { MonSmeIndustryPlanScene(options) }
    override val competitors by lazy { MonSmeCompetitorsPlanScene(options) }
    override val customers by lazy { MonSmeCustomerPlanScene(options) }
    override val suppliers by lazy { MonSmeSupplierPlanScene(options) }
    override val legal by lazy { MonSmeLegalPlanScene(options) }
    override val estate by lazy { MonSmeEstatePlanScene(options) }
}