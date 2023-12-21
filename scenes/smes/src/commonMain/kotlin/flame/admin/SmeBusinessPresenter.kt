@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.admin

import geo.AddressPresenter
import identifier.Industry
import kotlinx.JsExport

data class SmeBusinessPresenter(
    val src: SmeBusinessDto,
    val name: String?,
    val registration: String?,
    val phone: String?,
    val yearsInOperation: Int?,
    val address: AddressPresenter?,
    val numberOfJobs: Int?,
    val industry: Industry?,
    val businessStage: String?,
    val bbbee: String?,
    val staffComplement: String?,
    val description: String?,
)