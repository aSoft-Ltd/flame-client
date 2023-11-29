package flame

import cabinet.FileUploadParam
import koncurrent.Later
import koncurrent.later
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay

class SmeDocumentsApiFlix(options: SmeApiFlixOptions) : SmeFlixBaseApi(options), SmeDocumentsApi {
    override fun update(params: FileUploadParam) = upload(params)
}