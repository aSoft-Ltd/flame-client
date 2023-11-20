@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import cabinet.Directory
import flame.params.GenerateStatementParams
import flame.params.SendEmailParams
import flame.params.SendSMSParams
import hormone.Identified
import identifier.CorporateDto
import identifier.IndividualDto
import identifier.LegalEntityApi
import identifier.params.CorporateParams
import identifier.params.IndividualParams
import koncurrent.Later
import kotlin.js.JsExport
import kotlin.js.JsName

interface EntitiesApi : LegalEntityApi, Directory {

    @JsName("createCorporate")
    fun create(params: CorporateParams): Later<CorporateDto>

    @JsName("createIndividual")
    fun create(params: IndividualParams): Later<IndividualDto>

    fun generateStatementURL(params: GenerateStatementParams): Later<String>

    fun editIndividual(params: Identified<String, IndividualParams>): Later<IndividualDto>

    fun editCorporate(params: Identified<String, CorporateParams>): Later<CorporateDto>

    fun sendSMS(params: SendSMSParams): Later<SendSMSParams>

    fun sendEmail(params: SendEmailParams): Later<SendEmailParams>

    val contacts: ContactsApi
}