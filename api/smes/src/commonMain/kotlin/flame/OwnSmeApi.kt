@file:JsExport
package flame

import cabinet.Attachment
import cabinet.FileUploadParam
import kase.progress.ProgressBus
import kase.progress.VoidProgressBus
import koncurrent.Later
import kotlinx.JsExport

interface OwnSmeApi : OwnSmeScheme {
    fun upload(params: FileUploadParam, progress: ProgressBus = VoidProgressBus): Later<Attachment>
}