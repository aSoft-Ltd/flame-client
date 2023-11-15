@file:JsExport

package flame.routes.dashboard

import identifier.LegalEntityDto
import kollections.List
import kotlin.js.JsExport

data class DashboardData(val customers: List<LegalEntityDto>)