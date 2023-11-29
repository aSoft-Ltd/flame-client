@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package flame.routes.funding.acquisition

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
import kotlin.js.JsExport
import symphony.toForm

class SmeAcquisitionFormScene(
    private val options: SmeSceneOption<SmeApi>
) : FormScene<SmeAcquisitionFields>() {
    fun initialize() {
        ui.value = Loading("loading your information, please wait . . .")
        options.api.load().then {
            it.funding?.acquisition.toOutput()
        }.then {
            form(it)
        }.finally {
            ui.value = it.toLazyState()
        }
    }

    private fun form(output: SmeAcquisitionOutput) = SmeAcquisitionFields(output).toForm(
        heading = "For Acquisitions Only",
        details = "Enter acquisition information",
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