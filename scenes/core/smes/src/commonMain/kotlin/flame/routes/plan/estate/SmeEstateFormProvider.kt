@file:JsExport

package flame.routes.plan.estate

import flame.SmeDto
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.forms.plan.SmeQnAFields
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport
import symphony.toForm

internal class SmeEstateFormProvider(private val options: SmeSceneOptions<SmeScheme>) : SmePlanFormProvider {

    private val api = options.api

    override fun form(sme: SmeDto) = SmeQnAFields(sme.business.realEstate).toForm(
        heading = "Real Estate (Land & Buildings) Analysis",
        details = "Real Estate (Land & Buildings) Details",
        logger = options.logger
    ) {
        onSubmit { output ->
            api.update(sme.copy(business = sme.business.copy(legal = output)))
        }

        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}