@file:JsExport

package flame.routes.governance.management

import flame.routes.governance.management.committee.SmeManagementCommitteeScene
import flame.routes.governance.management.team.SmeManagementTeamScene
import kotlinx.JsExport

interface SmeManagementTheater {
    val team: SmeManagementTeamScene
    val committee: SmeManagementCommitteeScene
}