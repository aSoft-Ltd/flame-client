package flame.workers

import flame.SuppliersApi

class SupplierUploadAttachmentWorker(api: SuppliersApi) : UploadAttachmentWorker(api) {
    companion object {
        const val TYPE = "suppliers.attachment.upload"
    }
}