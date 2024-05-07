@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.routes.financial.office

import flame.SmeSceneOption
import flame.SmeScheme
import flame.forms.FormScene
import flame.forms.financial.office.SmeBackOfficeFields
import flame.forms.financial.office.SmeBackOfficeOutput
import flame.transformers.finance.toParams
import kase.Pending
import koncurrent.later.andThen
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

abstract class SmeBackOfficeFormScene(
    private val options: SmeSceneOption<SmeScheme>
) : FormScene<SmeBackOfficeFields>() {

    protected fun form(output: SmeBackOfficeOutput,details: String) = SmeBackOfficeFields(output).toForm(
        heading = "Business Details",
        details = details,
        logger = options.logger,
    ) {
        onCancel { ui.value = Pending }
        onSubmit { output ->
            output.toLater().then {
                output.toParams()
            }.andThen {
                options.api.update(it)
            }
        }
        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}