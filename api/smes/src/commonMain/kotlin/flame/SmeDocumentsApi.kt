package flame

import cabinet.FileUploadParam
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch

interface SmeDocumentsApi {
    fun update(params: FileUploadParam): Later<SmeDto>
}