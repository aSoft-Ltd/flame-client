package flame.workers

import cabinet.Attachment
import cabinet.FileUploadParam
import flame.SmeDto
import koncurrent.Later
import krest.Worker

class OwnSmeUploadDocumentWorker(private val options: OwnSmeUploadDocumentWorkerOptions) : Worker<FileUploadParam, Attachment> {
    override fun doWork(params: FileUploadParam): Later<Attachment> = options.api.upload(params)

    companion object {
        const val TYPE = "upload.sme.document"
    }
}