package flame

import cabinet.FileUploadParam
import koncurrent.Later

interface SmeDocumentsApi {
    fun update(params: FileUploadParam): Later<SmeDto>
}