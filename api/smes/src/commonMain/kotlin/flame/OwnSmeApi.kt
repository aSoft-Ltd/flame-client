@file:JsExport

package flame

import cabinet.Attachment
import cabinet.FileUploadParam
import epsilon.MemorySize
import kase.progress.ProgressBus
import kase.progress.SimpleProgression
import kase.progress.VoidProgressBus
import koncurrent.Later
import kotlinx.JsExport

interface OwnSmeApi : OwnSmeScheme {
    fun upload(params: FileUploadParam, onProgress: ((SimpleProgression<MemorySize>) -> Unit)? = null): Later<Attachment>
}