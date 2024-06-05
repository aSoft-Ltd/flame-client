@file:JsExport

package flame.routes.governance.advisory

import flame.SmePresenter
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.governance.SmeGovernanceDto
import flame.governance.SmeGoverningPersonnelDto
import flame.routes.governance.personnel.SmePersonnelScene
import kollections.List
import kollections.emptyList
import kotlinx.JsExport

sealed class SmeAdvisoryScene(options: SmeSceneOptions<SmeScheme>) : SmePersonnelScene(options) {

    override val title = "Advisor"

    protected fun SmePresenter.toAdvisors() = governance?.advisory ?: emptyList()

    override fun SmeGovernanceDto.updated(personnel: List<SmeGoverningPersonnelDto>) = copy(advisory = personnel)
}