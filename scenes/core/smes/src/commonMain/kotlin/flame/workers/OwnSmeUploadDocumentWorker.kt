package flame.workers

import cabinet.Attachment
import cabinet.FileUploadParam
import flame.SmeDto
import flame.documents.SMEDocumentUploadParam
import kase.progress.ProgressBus
import koncurrent.Later
import krest.Worker

class OwnSmeUploadDocumentWorker(private val options: OwnSmeUploadDocumentWorkerOptions) : Worker<SMEDocumentUploadParam, Attachment> {
    override fun doWork(params: SMEDocumentUploadParam, progress: ProgressBus): Later<Attachment> = options.api.upload(params)

    companion object {
        const val TYPE = "upload.sme.document"
    }
}