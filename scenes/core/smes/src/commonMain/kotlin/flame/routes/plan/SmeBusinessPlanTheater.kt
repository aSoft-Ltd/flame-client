@file:JsExport

package flame.routes.plan

import flame.routes.plan.utils.SmePlanSegmentScene
import kotlinx.JsExport

interface SmeBusinessPlanTheater {
    val marketing: SmePlanSegmentScene
    val services: SmePlanSegmentScene
    val industry: SmePlanSegmentScene
    val competitors: SmePlanSegmentScene
    val customers: SmePlanSegmentScene
    val suppliers: SmePlanSegmentScene
    val legal: SmePlanSegmentScene
    val estate: SmePlanSegmentScene
}