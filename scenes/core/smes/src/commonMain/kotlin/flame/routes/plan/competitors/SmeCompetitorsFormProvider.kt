@file:JsExport

package flame.routes.plan.competitors

import flame.SmeDto
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.forms.plan.SmeQnAFields
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport
import symphony.toForm

internal class SmeCompetitorsFormProvider(private val options: SmeSceneOptions<SmeScheme>) : SmePlanFormProvider {

    private val api = options.api

    override fun form(sme: SmeDto) = SmeQnAFields(sme.business.competition).toForm(
        heading = "Competitive Analysis",
        details = "Competitive Analysis Details",
        logger = options.logger
    ) {
        onSubmit { output ->
            api.update(sme.copy(business = sme.business.copy(competition = output)))
        }

        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}