@file:JsExport

package flame.routes.governance

import flame.SmeSceneOption
import flame.SmeScheme
import flame.forms.FormScene
import flame.forms.governance.SmeGovernanceFields
import flame.forms.governance.SmeGovernanceOutput
import flame.transformers.governance.toParams
import kase.Pending
import koncurrent.later.andThen
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

abstract class SmeGovernanceFormScene(private val options: SmeSceneOption<SmeScheme>) : FormScene<SmeGovernanceFields>() {
    protected fun form(output: SmeGovernanceOutput, details: String) = SmeGovernanceFields(output).toForm(
        heading = "Governance Details",
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