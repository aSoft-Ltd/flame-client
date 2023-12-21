@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package flame.routes.funding.breakdown

import flame.SmeApi
import flame.SmeSceneOption
import flame.transformers.admin.toOutput
import flame.transformers.admin.toParams
import flame.transformers.funding.toOutput
import flame.transformers.funding.toParams
import flame.utils.FormScene
import kase.Loading
import kase.Pending
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

class SmeBreakdownFormScene(
    private val options: SmeSceneOption<SmeApi>
) : FormScene<SmeBreakdownFields>() {
    fun initialize() {
        ui.value = Loading("loading your information, please wait . . .")
        options.api.load().then {
            it.funding?.breakdown.toOutput()
        }.then {
            form(it)
        }.finally {
            ui.value = it.toLazyState()
        }
    }

    private fun form(output: SmeBreakdownOutput) = SmeBreakdownFields(output).toForm(
        heading = "Breakdown of Funding Form",
        details = "Enter Breakdown of Funding Information",
        logger = options.logger,
    ) {
        onCancel { ui.value = Pending }
        onSubmit { output ->
            output.toLater().then {
                it.toParams()
            }.then {
                options.api.funding.update(it)
            }
        }
        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}