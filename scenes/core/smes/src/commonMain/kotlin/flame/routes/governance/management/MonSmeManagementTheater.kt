@file:JsExport
package flame.routes.governance.management

import flame.MonSmeScheme
import flame.OwnSmeApi
import flame.SmeSceneOptions
import flame.routes.governance.management.committee.MonSmeManagementCommitteeScene
import flame.routes.governance.management.committee.OwnSmeManagementCommitteeScene
import flame.routes.governance.management.team.MonSmeManagementTeamScene
import flame.routes.governance.management.team.OwnSmeManagementTeamScene
import kotlinx.JsExport

class MonSmeManagementTheater(options: SmeSceneOptions<MonSmeScheme>) : SmeManagementTheater {
    override val team by lazy { MonSmeManagementTeamScene(options) }
    override val committee by lazy { MonSmeManagementCommitteeScene(options) }
}