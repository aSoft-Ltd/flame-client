@file:JsExport

package flame.routes.plan.suppliers

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.utils.MonSmePlanSegmentScene
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport

class MonSmeSupplierPlanScene(
    private val options: SmeSceneOptions<MonSmeScheme>
) : MonSmePlanSegmentScene(options), SmePlanFormProvider by SmeSupplierFormProvider(options)