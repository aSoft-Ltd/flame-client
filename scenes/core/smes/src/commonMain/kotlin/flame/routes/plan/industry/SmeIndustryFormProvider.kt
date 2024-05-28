@file:JsExport

package flame.routes.plan.industry

import flame.SmeDto
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.forms.plan.SmeQnAFields
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport
import symphony.toForm

internal class SmeIndustryFormProvider(private val options: SmeSceneOptions<SmeScheme>) : SmePlanFormProvider {

    private val api = options.api

    override fun form(sme: SmeDto) = SmeQnAFields(sme.business.industry).toForm(
        heading = "Industry Analysis",
        details = "Industry Analysis Details",
        logger = options.logger
    ) {
        onSubmit { output ->
            api.update(sme.copy(business = sme.business.copy(industry = output)))
        }

        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}