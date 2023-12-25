@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.routes.financial.office

import flame.SmeApi
import flame.SmeSceneOption
import flame.transformers.finance.toOutput
import flame.transformers.finance.toParams
import flame.utils.FormScene
import kase.Loading
import kase.Pending
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.toLater
import koncurrent.later.then
import koncurrent.later.andThen
import kotlinx.JsExport
import symphony.toForm

class SmeBackOfficeFormScene(
    private val options: SmeSceneOption<SmeApi>
) : FormScene<SmeBackOfficeFields>() {
    fun initialize() {
        ui.value = Loading("Loading business information")
        options.api.load().then {
            it.finance?.office.toOutput()
        }.then {
            form(it)
        }.finally {
            ui.value = it.toLazyState()
        }
    }

    private fun form(output: SmeBackOfficeOutput) = SmeBackOfficeFields(output).toForm(
        heading = "Business Details",
        details = "Enter your business details here",
        logger = options.logger,
    ) {
        onCancel { ui.value = Pending }
        onSubmit { output ->
            output.toLater().then {
                output.toParams()
            }.andThen {
                options.api.finance.update(it)
            }
        }
        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}