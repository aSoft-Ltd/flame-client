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
import flame.transformers.utils.toProgress
import geo.toPresenter
import geo.transformers.toOutput
import kollections.*
import symphony.PhoneOutput


fun SmePlanDto.toProgress() = listOf(
    this.marketing.toQNProgress(),
    this.services.toQNProgress(),
    this.industry.toQNProgress(),
    this.competition.toQNProgress(),
    this.customers.toQNProgress(),
    this.suppliers.toQNProgress(),
    this.legal.toQNProgress(),
    this.realEstate.toQNProgress(),
).toProgress()


fun List<QnADto>.toQNProgress() = SmeSectionProgress(
    completed = filter {
        it.answer != null
    }.size,
    total = size
)
