@file:JsExport

package flame.routes.plan.competitors

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.utils.OwnSmePlanSegmentScene
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport

class OwnSmeCompetitorsPlanScene(
    private val options: SmeSceneOptions<OwnSmeScheme>
) : OwnSmePlanSegmentScene(options), SmePlanFormProvider by SmeCompetitorsFormProvider(options)