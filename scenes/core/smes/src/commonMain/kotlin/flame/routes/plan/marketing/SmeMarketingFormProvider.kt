@file:JsExport

package flame.routes.plan.marketing

import flame.SmeDto
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.forms.plan.SmeQnAFields
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport
import symphony.toForm

internal class SmeMarketingFormProvider(private val options: SmeSceneOptions<SmeScheme>) : SmePlanFormProvider {

    private val api = options.api

    override fun form(sme: SmeDto) = SmeQnAFields(sme.business.marketing).toForm(
        heading = "Marketing Plan",
        details = "Marketing Plan details",
        logger = options.logger
    ) {
        onSubmit { output ->
            api.update(sme.copy(business = sme.business.copy(marketing = output)))
        }

        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}