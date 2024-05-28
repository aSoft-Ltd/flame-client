@file:JsExport

package flame.routes.plan.competitors

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.utils.MonSmePlanSegmentScene
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport

class MonSmeCompetitorsPlanScene(
    private val options: SmeSceneOptions<MonSmeScheme>
) : MonSmePlanSegmentScene(options), SmePlanFormProvider by SmeCompetitorsFormProvider(options)