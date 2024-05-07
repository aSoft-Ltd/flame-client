package flame.workers

import cabinet.Attachment
import cabinet.FileUploadParam
import epsilon.FileBlob
import flame.EntitiesApi
import kase.progress.Progress
import kase.progress.ProgressBus
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import krest.Worker
import flame.workers.UploadAttachmentWorkerParams as Params

abstract class UploadAttachmentWorker(val api: EntitiesApi) : Worker<Params, Attachment> {

    override fun doWork(params: Params, progress: ProgressBus): Later<Attachment> {
        return api.rootDir(params.entityId).upload(params.toFileUploadParam())
    }

    private fun Params.toFileUploadParam() = FileUploadParam(
        path = "customers/$entityId/attachments",
        filename = filename,
        file = file
    )
}