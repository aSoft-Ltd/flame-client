package flame.workers

import epsilon.RawFile

data class UploadAttachmentWorkerParams(
    val entityId: String,
    val file: RawFile,
    val filename: String
)