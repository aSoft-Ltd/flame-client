@file:JsExport

package flame.routes.plan.industry

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.utils.MonSmePlanSegmentScene
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport

class MonSmeIndustryPlanScene(
    private val options: SmeSceneOptions<MonSmeScheme>
) : MonSmePlanSegmentScene(options), SmePlanFormProvider by SmeIndustryFormProvider(options)