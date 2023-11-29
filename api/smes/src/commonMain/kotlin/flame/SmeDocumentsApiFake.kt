package flame

import cabinet.FileUploadParam
import koncurrent.Later
import koncurrent.later
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay

// TODO: Just remove this if the flix implementation has worked
class SmeDocumentsApiFake(private val options: SmeApiFlixOptions) : SmeDocumentsApi {
    override fun update(params: FileUploadParam): Later<SmeDto> = options.scope.later {
        val (uploading) = it.setStages("Uploading")
        for (i in 0..100) {
            delay((600..1000).random().milliseconds)
            it.updateProgress(uploading(i, 100))
        }
        SmeDto("")
    }
}