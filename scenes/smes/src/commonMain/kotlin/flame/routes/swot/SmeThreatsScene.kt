@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.swot

import flame.SmeApi
import flame.SmeDto
import flame.SmeSceneOption
import kollections.List
import kollections.emptyList
import kotlinx.JsExport

class SmeThreatsScene(options: SmeSceneOption<SmeApi>) : SmeSwotComponentScene(options) {
    override val component by lazy { "threat" }
    override val api by lazy { options.api.swot.threats }
    override fun SmeDto.toList(): List<String> = swot?.threats ?: emptyList()
}