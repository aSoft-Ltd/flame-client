package flame.workers

import flame.CustomersApi

class CustomerUploadAttachmentWorker(api: CustomersApi) : UploadAttachmentWorker(api) {
    companion object {
        const val TYPE = "customers.attachment.upload"
    }
}