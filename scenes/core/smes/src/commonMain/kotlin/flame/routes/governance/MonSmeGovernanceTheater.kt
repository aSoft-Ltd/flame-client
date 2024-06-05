@file:JsExport
package flame.routes.governance

import flame.MonSmeScheme
import flame.OwnSmeApi
import flame.SmeSceneOptions
import flame.routes.governance.directors.MonSmeDirectorsScene
import flame.routes.governance.directors.OwnSmeDirectorsScene
import flame.routes.governance.management.MonSmeManagementTheater
import flame.routes.governance.management.OwnSmeManagementTheater
import flame.routes.governance.manpower.MonSmeManPowerFormScene
import flame.routes.governance.manpower.OwnSmeManPowerFormScene
import kotlinx.JsExport

class MonSmeGovernanceTheater(options: SmeSceneOptions<MonSmeScheme>) : SmeGovernanceTheater {
    override val manpower by lazy { MonSmeManPowerFormScene(options) }
    override val directors by lazy { MonSmeDirectorsScene(options) }
    override val management by lazy { MonSmeManagementTheater(options) }
}