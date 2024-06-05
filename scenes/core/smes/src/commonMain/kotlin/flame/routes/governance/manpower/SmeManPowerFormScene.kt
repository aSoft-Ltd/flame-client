@file:JsExport

package flame.routes.governance.manpower

import flame.SmeSceneOptions
import flame.SmeScheme
import flame.forms.FormScene
import flame.forms.governance.SmeManPowerFields
import flame.forms.governance.SmeManPowerOutput
import flame.transformers.governance.toParams
import kase.Pending
import koncurrent.later.andThen
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

abstract class SmeManPowerFormScene(private val options: SmeSceneOptions<SmeScheme>) : FormScene<SmeManPowerFields>() {
    protected fun form(output: SmeManPowerOutput, details: String) = SmeManPowerFields(output).toForm(
        heading = "Manpower Details",
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