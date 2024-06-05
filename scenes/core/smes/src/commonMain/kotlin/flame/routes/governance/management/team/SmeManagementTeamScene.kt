@file:JsExport

package flame.routes.governance.management.team

import flame.SmePresenter
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.governance.SmeGovernanceDto
import flame.governance.SmeGoverningPersonnelDto
import flame.governance.SmeManagementDto
import flame.routes.governance.personnel.SmePersonnelScene
import kollections.List
import kollections.emptyList
import kotlinx.JsExport

sealed class SmeManagementTeamScene(options: SmeSceneOptions<SmeScheme>) : SmePersonnelScene(options) {

    override val title = "Member"

    protected fun SmePresenter.toTeam() = governance?.management?.team ?: emptyList()

    override fun SmeGovernanceDto.updated(personnel: List<SmeGoverningPersonnelDto>) : SmeGovernanceDto {
        val stale = management ?: SmeManagementDto(emptyList(), emptyList())
        return copy(management = stale.copy(team = personnel))
    }
}