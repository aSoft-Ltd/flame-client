@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.swot

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.forms.swot.SwotComponent
import kotlinx.JsExport

class OwnSmeSwotTheater(options: SmeSceneOptions<OwnSmeScheme>) : SmeSwotTheater{
    override val strengths by lazy {
        OwnSmeSwotComponentScene(options, SwotComponent.strenght) { it?.strengths }
    }

    override val weaknesses by lazy {
        OwnSmeSwotComponentScene(options, SwotComponent.weakness) { it?.weaknesses }
    }

    override val opportunities by lazy {
        OwnSmeSwotComponentScene(options, SwotComponent.opportunity) { it?.opportunities }
    }

    override val threats by lazy {
        OwnSmeSwotComponentScene(options, SwotComponent.threat) { it?.threats }
    }
}