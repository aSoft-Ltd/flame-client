@file:JsExport
package flame.routes.governance

import flame.routes.governance.directors.SmeDirectorsScene
import flame.routes.governance.management.SmeManagementTheater
import flame.routes.governance.manpower.SmeManPowerFormScene
import kotlinx.JsExport

interface SmeGovernanceTheater {
    val manpower: SmeManPowerFormScene
    val directors: SmeDirectorsScene
    val management: SmeManagementTheater
}