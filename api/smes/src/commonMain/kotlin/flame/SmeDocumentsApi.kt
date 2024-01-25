package flame

import cabinet.FileUploadParam
import koncurrent.Later

interface SmeDocumentsApi : SmeDocumentsScheme {
    fun update(params: FileUploadParam): Later<SmeDto>
}