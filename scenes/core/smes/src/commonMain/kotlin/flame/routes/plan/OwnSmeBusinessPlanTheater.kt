@file:JsExport

package flame.routes.plan

import flame.OwnSmeApi
import flame.SmeSceneOptions
import flame.routes.plan.competitors.OwnSmeCompetitorsPlanScene
import flame.routes.plan.customers.OwnSmeCustomerPlanScene
import flame.routes.plan.estate.OwnSmeEstatePlanScene
import flame.routes.plan.industry.OwnSmeIndustryPlanScene
import flame.routes.plan.legal.OwnSmeLegalPlanScene
import flame.routes.plan.marketing.OwnSmeMarketPlanScene
import flame.routes.plan.services.OwnSmeServicesPlanScene
import flame.routes.plan.suppliers.OwnSmeSupplierPlanScene
import kotlinx.JsExport

class OwnSmeBusinessPlanTheater(options: SmeSceneOptions<OwnSmeApi>) : SmeBusinessPlanTheater {
    override val marketing by lazy { OwnSmeMarketPlanScene(options) }
    override val services by lazy { OwnSmeServicesPlanScene(options) }
    override val industry by lazy { OwnSmeIndustryPlanScene(options) }
    override val competitors by lazy { OwnSmeCompetitorsPlanScene(options) }
    override val customers by lazy { OwnSmeCustomerPlanScene(options) }
    override val suppliers by lazy { OwnSmeSupplierPlanScene(options) }
    override val legal by lazy { OwnSmeLegalPlanScene(options) }
    override val estate by lazy { OwnSmeEstatePlanScene(options) }
}