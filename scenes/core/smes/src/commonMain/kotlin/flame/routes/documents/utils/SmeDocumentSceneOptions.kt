package flame.routes.documents.utils

import flame.documents.SmeDocument
import flame.workers.OwnSmeUploadDocumentWorker
import krest.WorkManager

class SmeDocumentSceneOptions(
    val document: SmeDocument,
    val wm: WorkManager
) {
    val path by lazy { "documents" }
    val topic by lazy { "$path/${document.label}" }
    val type by lazy { OwnSmeUploadDocumentWorker.TYPE }
}