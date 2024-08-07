@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.swot

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.forms.swot.SwotComponent
import kotlinx.JsExport

class MonSmeSwotTheater(options: SmeSceneOptions<MonSmeScheme>) : SmeSwotTheater{
    override val strengths by lazy {
        MonSmeSwotComponentScene(options, SwotComponent.strength) { it?.strengths }
    }

    override val weaknesses by lazy {
        MonSmeSwotComponentScene(options, SwotComponent.weakness) { it?.weaknesses }
    }

    override val opportunities by lazy {
        MonSmeSwotComponentScene(options, SwotComponent.opportunity) { it?.opportunities }
    }

    override val threats by lazy {
        MonSmeSwotComponentScene(options, SwotComponent.threat) { it?.threats }
    }
}