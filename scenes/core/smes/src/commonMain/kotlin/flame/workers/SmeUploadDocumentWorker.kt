package flame.workers

import cabinet.FileUploadParam
import flame.SmeDto
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import krest.Worker

class SmeUploadDocumentWorker(private val options: SmeUploadDocumentWorkerOptions) : Worker<FileUploadParam, SmeDto> {
    override fun doWork(params: FileUploadParam): Later<SmeDto> = options.api.documents.update(params)

    companion object {
        const val TYPE = "upload.sme.document"
    }
}