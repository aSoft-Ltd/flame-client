package flame

import cabinet.FileUploadParam

class SmeDocumentsApiFlix(options: SmeApiFlixOptions) : SmeFlixBaseApi(options), SmeDocumentsApi {
    override fun update(params: FileUploadParam) = upload(params)
}