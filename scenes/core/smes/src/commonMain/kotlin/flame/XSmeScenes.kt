@file:JsExport

package flame

import flame.route.admin.SmeAdminScenes
import flame.route.documents.SmeDocumentsScenes
import flame.route.financial.SmeFinancialScenes
import flame.route.info.SmeInfoScene2
import kotlinx.JsExport

interface XSmeScenes {
    val admin: SmeAdminScenes
    val info: SmeInfoScene2
    val documents: SmeDocumentsScenes
    val finance: SmeFinancialScenes
}