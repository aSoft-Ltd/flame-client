@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.CustomerScenesConfig
import flame.CustomersApi
import flame.workers.CustomerUploadAttachmentWorker
import kotlin.js.JsExport

class CustomerAttachmentsScene(config: CustomerScenesConfig<CustomersApi>) : EntityAttachmentsScene(config) {
    override val Type = CustomerUploadAttachmentWorker.TYPE
}