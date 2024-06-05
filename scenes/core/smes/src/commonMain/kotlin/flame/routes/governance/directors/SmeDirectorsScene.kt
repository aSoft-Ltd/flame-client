@file:JsExport

package flame.routes.governance.directors

import flame.SmePresenter
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.governance.SmeGovernanceDto
import flame.governance.SmeGoverningPersonnelDto
import flame.routes.governance.personnel.SmePersonnelScene
import kollections.List
import kollections.emptyList
import kotlinx.JsExport

sealed class SmeDirectorsScene(options: SmeSceneOptions<SmeScheme>) : SmePersonnelScene(options) {

    override val title = "Director"

    protected fun SmePresenter.toDirectors() = governance?.directors ?: emptyList()

    override fun SmeGovernanceDto.updated(personnel: List<SmeGoverningPersonnelDto>) = copy(directors = personnel)
}