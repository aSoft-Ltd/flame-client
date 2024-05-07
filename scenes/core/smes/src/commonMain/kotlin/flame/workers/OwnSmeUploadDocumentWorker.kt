package flame.workers

import cabinet.Attachment
import cabinet.FileUploadParam
import flame.SmeDto
import kase.progress.ProgressBus
import koncurrent.Later
import krest.Worker

class OwnSmeUploadDocumentWorker(private val options: OwnSmeUploadDocumentWorkerOptions) : Worker<FileUploadParam, Attachment> {
    override fun doWork(params: FileUploadParam, progress: ProgressBus): Later<Attachment> = options.api.upload(params, progress)

    companion object {
        const val TYPE = "upload.sme.document"
    }
}