@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.SmeDto
import flame.SmeSectionProgress
import flame.admin.SmeAdminDto
import flame.admin.SmeBusinessDto
import flame.admin.SmeBusinessPresenter
import flame.forms.admin.business.SmeBusinessOutput
import flame.plan.QnADto
import flame.plan.SmePlanDto
import flame.transformers.governance.toProgress
import flame.transformers.utils.aggregate
import flame.transformers.utils.toProgress
import geo.toPresenter
import geo.transformers.toOutput
import kollections.*
import symphony.PhoneOutput


fun SmePlanDto.toProgress():SmeSectionProgress {
    val total = listOf(
        this.marketing.toQNProgress("Marketing"),
        this.services.toQNProgress("Services"),
        this.industry.toQNProgress("Industry"),
        this.competition.toQNProgress("Competition"),
        this.customers.toQNProgress("Customers"),
        this.suppliers.toQNProgress("Suppliers"),
        this.legal.toQNProgress("Legal"),
        this.realEstate.toQNProgress("Real Estate"),
    ).aggregate()
    println("Business Plan: ${total.info}")
    return total;
}

fun List<QnADto>.toQNProgress(label:String):SmeSectionProgress {

    val progress = SmeSectionProgress(
        completed = filter {
            it.answer != null
        }.size,
        total = size
    )
    println("$label: ${progress.info}")
    return progress
}
