@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.swot

import flame.SmeApi
import flame.SmeDto
import flame.SmeSceneOption
import kollections.List
import kollections.iEmptyList
import kollections.toIList
import kotlin.js.JsExport

class SmeOpportunitiesScene(options: SmeSceneOption<SmeApi>) : SmeSwotComponentScene(options) {
    override val component by lazy { "opportunity" }
    override val api by lazy { options.api.swot.opportunities }
    override fun SmeDto.toList(): List<String> = swot?.opportunities?.toIList() ?: iEmptyList()
}