@file:JsExport

package flame.routes.governance.management.committee

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

sealed class SmeManagementCommitteeScene(options: SmeSceneOptions<SmeScheme>) : SmePersonnelScene(options) {

    override val title = "Member"

    protected fun SmePresenter.toMember() = governance?.management?.committee ?: emptyList()

    override fun SmeGovernanceDto.updated(personnel: List<SmeGoverningPersonnelDto>) : SmeGovernanceDto {
        val stale = management ?: SmeManagementDto(emptyList(), emptyList())
        return copy(management = stale.copy(committee = personnel))
    }
}