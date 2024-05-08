@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package flame.routes.funding.breakdown

import flame.SmeSceneOptions
import flame.forms.FormScene
import flame.transformers.funding.toParams
import kase.Loading
import kase.Pending
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

class SmeBreakdownFormScene(
    private val options: SmeSceneOptions<Any>
) : FormScene<SmeBreakdownFields>() {
    fun initialize() {
        ui.value = Loading("loading your information, please wait . . .")
//        options.api.load().then {
//            it.funding?.breakdown.toOutput()
//        }.then {
//            form(it)
//        }.finally {
//            ui.value = it.toLazyState()
//        }
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
//                options.api.funding.update(it)
            }
        }
        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}