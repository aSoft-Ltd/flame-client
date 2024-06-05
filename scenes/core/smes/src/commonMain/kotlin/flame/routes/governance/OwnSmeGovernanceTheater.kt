@file:JsExport
package flame.routes.governance

import flame.OwnSmeApi
import flame.SmeSceneOptions
import flame.routes.governance.directors.OwnSmeDirectorsScene
import flame.routes.governance.management.OwnSmeManagementTheater
import flame.routes.governance.manpower.OwnSmeManPowerFormScene
import kotlinx.JsExport

class OwnSmeGovernanceTheater(options: SmeSceneOptions<OwnSmeApi>) : SmeGovernanceTheater {
    override val manpower by lazy { OwnSmeManPowerFormScene(options) }
    override val directors by lazy { OwnSmeDirectorsScene(options) }
    override val management by lazy { OwnSmeManagementTheater(options) }
}