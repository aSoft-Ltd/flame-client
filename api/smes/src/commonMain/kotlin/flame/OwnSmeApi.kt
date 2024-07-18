@file:JsExport

package flame

import cabinet.Attachment
import cabinet.FileUploadParam
import epsilon.MemorySize
import koncurrent.Later
import kotlinx.JsExport
import status.SilentStagedProgressPublisher
import status.StagedProgressPublisher

interface OwnSmeApi : OwnSmeScheme {

    fun upload(params: FileUploadParam, progress: StagedProgressPublisher<MemorySize> = SilentStagedProgressPublisher()): Later<Attachment>
    fun xlsx(params: FileUploadParam, progress: StagedProgressPublisher<MemorySize> = SilentStagedProgressPublisher()): Later<Any>
}