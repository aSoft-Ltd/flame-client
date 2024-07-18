package flame.workers

import cabinet.Attachment
import cabinet.FileUploadParam
import kase.progress.ProgressBus
import koncurrent.Later
import krest.Worker

class OwnSmeUploadXLSXWorker(private val options: OwnSmeUploadDocumentWorkerOptions) : Worker<FileUploadParam, Any> {
    override fun doWork(params: FileUploadParam, progress: ProgressBus): Later<Any> = options.api.xlsx(params)

    companion object {
        const val TYPE = "upload.sme.xlsx"
    }
}