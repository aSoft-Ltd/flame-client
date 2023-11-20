package flame.workers

import cabinet.Attachment
import cabinet.FileUploadParam
import epsilon.FileBlob
import flame.EntitiesApi
import koncurrent.Later
import krest.Worker
import flame.workers.UploadAttachmentWorkerParams as Params

abstract class UploadAttachmentWorker(val api: EntitiesApi) : Worker<Params, Attachment> {

    override fun doWork(params: Params): Later<Attachment> {
        return api.rootDir(params.entityId).upload(params.toFileUploadParam())
    }

    private fun Params.toFileUploadParam() = FileUploadParam(
        path = "customers/$entityId/attachments",
        filename = filename,
        file = file
    )
}