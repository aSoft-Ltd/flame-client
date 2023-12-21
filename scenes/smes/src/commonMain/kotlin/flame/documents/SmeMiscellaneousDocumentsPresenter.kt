@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.documents

import cabinet.Attachment
import kotlinx.JsExport

data class SmeMiscellaneousDocumentsPresenter(
    val letterOfIntent: Attachment? = null,
    val quotations: Attachment? = null,
    val mouOrSaleOfAgreements: Attachment? = null
)