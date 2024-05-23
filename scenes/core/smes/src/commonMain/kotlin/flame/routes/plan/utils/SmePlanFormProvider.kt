package flame.routes.plan.utils

import flame.SmeDto
import flame.forms.plan.SmeQnAFields
import flame.plan.QnADto
import kollections.List
import symphony.Form

interface SmePlanFormProvider {
    fun form(sme: SmeDto): Form<SmeDto, List<QnADto>, SmeQnAFields>
}