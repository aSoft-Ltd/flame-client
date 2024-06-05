@file:JsExport
package flame.routes.governance.management

import flame.OwnSmeApi
import flame.SmeSceneOptions
import flame.routes.governance.management.committee.OwnSmeManagementCommitteeScene
import flame.routes.governance.management.team.OwnSmeManagementTeamScene
import kotlinx.JsExport

class OwnSmeManagementTheater(options: SmeSceneOptions<OwnSmeApi>) : SmeManagementTheater {
    override val team by lazy { OwnSmeManagementTeamScene(options) }
    override val committee by lazy { OwnSmeManagementCommitteeScene(options) }
}