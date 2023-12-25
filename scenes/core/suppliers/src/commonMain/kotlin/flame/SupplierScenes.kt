@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import flame.routes.SuppliersScene
import flame.routes.add.SupplierAddScene
import flame.routes.uid.SupplierAttachmentsScene
import flame.routes.uid.EntityDuplicateScene
import flame.routes.uid.EntityEditScene
import flame.routes.uid.SupplierGenerateStatementScene
import flame.routes.uid.SupplierMessagesScene
import flame.routes.uid.SupplierScene
import flame.routes.uid.SupplierSendMessageScene
import kotlinx.JsExport

interface SupplierScenes {
    val suppliers: SuppliersScene
    val supplier: SupplierScene
    val supplierAttachments: SupplierAttachmentsScene
    val supplierAdd: SupplierAddScene
    val supplierDuplicate: EntityDuplicateScene
    val supplierEdit: EntityEditScene
    val supplierGenerateStatement: SupplierGenerateStatementScene
    val supplierSendMessage: SupplierSendMessageScene
    val supplierMessages: SupplierMessagesScene
    val supplierInfo: SupplierScene
    val supplierOverview: SupplierScene
}