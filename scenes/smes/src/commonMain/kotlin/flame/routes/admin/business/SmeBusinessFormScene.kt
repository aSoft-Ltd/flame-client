@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.routes.admin.business

import flame.SmeApi
import flame.SmeSceneOption
import flame.transformers.admin.toOutput
import flame.transformers.admin.toParams
import flame.utils.FormScene
import kase.Loading
import kase.Pending
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

class SmeBusinessFormScene(
    private val options: SmeSceneOption<SmeApi>
) : FormScene<SmeBusinessFields>() {
    fun initialize() {
        ui.value = Loading("Loading business information")
        options.api.load().then {
            it.admin?.business.toOutput()
        }.then {
            form(it)
        }.finally {
            ui.value = it.toLazyState()
        }
    }

    private fun form(output: SmeBusinessOutput) = SmeBusinessFields(output).toForm(
        heading = "Business Details",
        details = "Enter your business details here",
        logger = options.logger,
    ) {
        onCancel { ui.value = Pending }
        onSubmit { output ->
            output.toLater().then {
                output.toParams()
            }.andThen {
                options.api.admin.update(it)
            }
        }
        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}