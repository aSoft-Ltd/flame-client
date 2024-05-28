@file:JsExport

package flame.routes.plan.suppliers

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.utils.OwnSmePlanSegmentScene
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport

class OwnSmeSupplierPlanScene(
    private val options: SmeSceneOptions<OwnSmeScheme>
) : OwnSmePlanSegmentScene(options), SmePlanFormProvider by SmeSupplierFormProvider(options)