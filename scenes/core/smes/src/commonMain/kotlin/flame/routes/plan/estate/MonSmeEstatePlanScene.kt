@file:JsExport

package flame.routes.plan.estate

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.routes.plan.utils.MonSmePlanSegmentScene
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport

class MonSmeEstatePlanScene(
    private val options: SmeSceneOptions<MonSmeScheme>
) : MonSmePlanSegmentScene(options), SmePlanFormProvider by SmeEstateFormProvider(options)