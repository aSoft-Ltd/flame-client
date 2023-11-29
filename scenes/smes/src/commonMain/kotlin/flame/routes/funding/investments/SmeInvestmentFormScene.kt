@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.routes.funding.investments

import flame.SmeApi
import flame.SmeSceneOption
import flame.transformers.funding.toOutput
import flame.transformers.funding.toParams
import flame.utils.FormScene
import kase.Loading
import kase.Pending
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.toLater
import kotlin.js.JsExport
import symphony.toForm

class SmeInvestmentFormScene(
    private val options: SmeSceneOption<SmeApi>
) : FormScene<SmeInvestmentFields>() {
    fun initialize() {
        ui.value = Loading("Loading business information")
        options.api.load().then {
            it.funding?.investment.toOutput()
        }.then {
            form(it)
        }.finally {
            ui.value = it.toLazyState()
        }
    }

    private fun form(output: SmeInvestmentOutput) = SmeInvestmentFields(output).toForm(
        heading = "Investment Details",
        details = "Enter your investment details here",
        logger = options.logger,
    ) {
        onCancel { ui.value = Pending }
        onSubmit { output ->
            output.toLater().then {
                output.toParams()
            }.andThen {
                options.api.funding.update(it)
            }
        }
        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}