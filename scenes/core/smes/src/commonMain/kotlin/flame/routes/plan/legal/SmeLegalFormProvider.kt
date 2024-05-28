@file:JsExport

package flame.routes.plan.legal

import flame.SmeDto
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.forms.plan.SmeQnAFields
import flame.routes.plan.utils.SmePlanFormProvider
import kotlinx.JsExport
import symphony.toForm

internal class SmeLegalFormProvider(private val options: SmeSceneOptions<SmeScheme>) : SmePlanFormProvider {

    private val api = options.api

    override fun form(sme: SmeDto) = SmeQnAFields(sme.business.legal).toForm(
        heading = "Legal and Compliance Analysis",
        details = "Legal and Compliance Details",
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