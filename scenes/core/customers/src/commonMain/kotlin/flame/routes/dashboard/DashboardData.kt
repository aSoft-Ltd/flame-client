@file:JsExport

package flame.routes.dashboard

import identifier.LegalEntityDto
import kollections.List
import kotlinx.JsExport

data class DashboardData(val customers: List<LegalEntityDto>)