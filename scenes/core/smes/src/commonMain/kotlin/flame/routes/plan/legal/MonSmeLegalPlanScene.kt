@file:JsExport

package flame.routes.plan.legal

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.utils.MonSmePlanSegmentScene
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport

class MonSmeLegalPlanScene(
    private val options: SmeSceneOptions<MonSmeScheme>
) : MonSmePlanSegmentScene(options), SmePlanFormProvider by SmeLegalFormProvider(options)