@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.swot

import flame.SmeApi
import flame.SmeDto
import flame.SmeSceneOption
import kollections.List
import kollections.emptyList
import kotlinx.JsExport

class SmeWeaknessesScene(options: SmeSceneOption<SmeApi>) : SmeSwotComponentScene(options) {
    override val component by lazy { "weakness" }
    override val api by lazy { options.api.swot.weaknesses }
    override fun SmeDto.toList(): List<String> = swot?.weaknesses ?: emptyList()
}