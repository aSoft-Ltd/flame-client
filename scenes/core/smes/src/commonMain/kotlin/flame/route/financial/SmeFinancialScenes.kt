@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.route.financial

import flame.forms.XSmeFormScene
import flame.forms.financial.status.SmeFinancialStatusFields
import flame.route.financial.office.SmeBackOfficeFormScene
import kotlinx.JsExport

sealed interface SmeFinancialScenes {
    val office: SmeBackOfficeFormScene

    val status : XSmeFormScene<SmeFinancialStatusFields>

//    val acquisition
}