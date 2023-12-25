@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.SupplierScenesConfig
import flame.SuppliersApi
import flame.workers.SupplierUploadAttachmentWorker
import kotlinx.JsExport

class SupplierAttachmentsScene(config: SupplierScenesConfig<SuppliersApi>) : EntityAttachmentsScene(config) {
    override val Type = SupplierUploadAttachmentWorker.TYPE
}