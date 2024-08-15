@file:JsExport

package flame

import cabinet.Attachment
import cabinet.FileUploadParam
import epsilon.MemorySize
import flame.documents.SMEDocumentUploadParam
import flame.documents.SmeDocument
import koncurrent.Later
import kotlinx.JsExport
import status.SilentStagedProgressPublisher
import status.StagedProgressPublisher

interface OwnSmeApi : OwnSmeScheme {

    fun upload(params: SMEDocumentUploadParam, progress: StagedProgressPublisher<MemorySize> = SilentStagedProgressPublisher()): Later<Attachment>
    fun xlsx(params: FileUploadParam, progress: StagedProgressPublisher<MemorySize> = SilentStagedProgressPublisher()): Later<Any>
    fun uploadResume(params: FileUploadParam, progress: StagedProgressPublisher<MemorySize> = SilentStagedProgressPublisher()): Later<Attachment>

    fun deleteDocument(document: SmeDocument): Later<SmeDto>
}