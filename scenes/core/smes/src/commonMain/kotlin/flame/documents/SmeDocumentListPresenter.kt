@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.documents

import cabinet.AttachmentPresenter
import kotlinx.JsExport

data class SmeDocumentListPresenter(
    val companyProfile: AttachmentPresenter? = null,
    val businessPlan: AttachmentPresenter? = null,
    val companyRegistrationDocument: AttachmentPresenter? = null,
    val shareholderRegister: AttachmentPresenter? = null,
    val taxPin: AttachmentPresenter? = null,
    val fundingApplicationRequest: AttachmentPresenter? = null,
    val beeAffidavit: AttachmentPresenter? = null,
    val pithDeck: AttachmentPresenter? = null,
)
