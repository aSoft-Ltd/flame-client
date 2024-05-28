@file:JsExport

package flame.routes.plan.legal

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.utils.OwnSmePlanSegmentScene
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport

class OwnSmeLegalPlanScene(
    private val options: SmeSceneOptions<OwnSmeScheme>
) : OwnSmePlanSegmentScene(options), SmePlanFormProvider by SmeLegalFormProvider(options)