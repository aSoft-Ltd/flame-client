@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.documents

import cabinet.Attachment
import kotlin.js.JsExport

data class SmeDocumentListPresenter(
    val companyProfile: Attachment? = null,
    val businessPlan: Attachment? = null,
    val companyRegistrationDocument: Attachment? = null,
    val shareholderRegister: Attachment? = null,
    val taxPin: Attachment? = null,
    val fundingApplicationRequest: Attachment? = null,
    val beeAffidavit: Attachment? = null,
    val pithDeck: Attachment? = null,
)
