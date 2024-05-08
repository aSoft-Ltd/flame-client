@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents.utils

import cabinet.AttachmentPresenter
import flame.SmeSectionProgress
import kotlinx.JsExport

sealed interface SmeDocumentState {
    val asUninitialized get() = this as? SmeDocumentUninitialized
    val asNotUploaded get() = this as? SmeDocumentNotUploaded
    val asUploading get() = this as? SmeDocumentUploadingProgress
    val asUploaded get() = this as? SmeDocumentUploaded
    val asFailed get() = this as? SmeDocumentUploadFailed
}

data object SmeDocumentUninitialized : SmeDocumentState

data object SmeDocumentNotUploaded: SmeDocumentState

data class SmeDocumentUploadingProgress(
    val progress: SmeSectionProgress
) : SmeDocumentState

data class SmeDocumentUploaded(
    val attachment: AttachmentPresenter
) : SmeDocumentState

data class SmeDocumentUploadFailed(
    val cause: Throwable
): SmeDocumentState