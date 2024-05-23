@file:JsExport

package flame.routes.plan.marketing

import flame.MonSmeScheme
import flame.OwnSmeApi
import flame.SmeDto
import flame.SmeSceneOptions
import flame.forms.plan.SmeQnAFields
import flame.routes.plan.utils.MonSmePlanSegmentScene
import flame.routes.plan.utils.OwnSmePlanSegmentScene
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport
import symphony.toForm

class MonSmeMarketPlanScene(
    private val options: SmeSceneOptions<MonSmeScheme>
) : MonSmePlanSegmentScene(options), SmePlanFormProvider by SmeMarketingFormProvider(options)