@file:JsExport

package flame.routes.plan.industry

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.utils.OwnSmePlanSegmentScene
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport

class OwnSmeIndustryPlanScene(
    private val options: SmeSceneOptions<OwnSmeScheme>
) : OwnSmePlanSegmentScene(options), SmePlanFormProvider by SmeIndustryFormProvider(options)