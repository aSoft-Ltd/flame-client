@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.documents

import cabinet.AttachmentPresenter
import kotlinx.JsExport

data class SmeMiscellaneousDocumentsPresenter(
    val letterOfIntent: AttachmentPresenter? = null,
    val quotations: AttachmentPresenter? = null,
    val mouOrSaleOfAgreements: AttachmentPresenter? = null
)