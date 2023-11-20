@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import flame.routes.CustomersScene
import flame.routes.add.CustomerAddScene
import flame.routes.dashboard.CustomersDashboardScene
import flame.routes.uid.CustomerAttachmentsScene
import flame.routes.uid.CustomerDuplicateScene
import flame.routes.uid.CustomerEditScene
import flame.routes.uid.CustomerGenerateStatementScene
import flame.routes.uid.CustomerMessagesScene
import flame.routes.uid.CustomerScene
import flame.routes.uid.CustomerSendMessageScene
import kotlin.js.JsExport

interface CustomerScenes {
    val customers: CustomersScene
    val customersDashboard: CustomersDashboardScene
    val customer: CustomerScene
    val customerAttachments: CustomerAttachmentsScene
    val customerAdd: CustomerAddScene
    val customerDuplicate: CustomerDuplicateScene
    val customerEdit: CustomerEditScene
    val customerGenerateStatement: CustomerGenerateStatementScene
    val customerSendMessage: CustomerSendMessageScene
    val customerMessages: CustomerMessagesScene
    val customerInfo: CustomerScene
    val customerExpenses: CustomerScene
    val customerOverview: CustomerScene
    val customerProjects: CustomerScene
}