@file:JsExport

package flame.routes.plan.marketing

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.utils.OwnSmePlanSegmentScene
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport

class OwnSmeMarketPlanScene(
    private val options: SmeSceneOptions<OwnSmeScheme>
) : OwnSmePlanSegmentScene(options), SmePlanFormProvider by SmeMarketingFormProvider(options)