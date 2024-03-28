@file:JsExport

package flame.routes.admin.business

import flame.SmeSceneOption
import flame.XSmeScheme
import flame.forms.FormScene
import flame.forms.admin.business.SmeBusinessFields
import flame.forms.admin.business.SmeBusinessOutput
import flame.transformers.admin.toParams
import kase.Pending
import koncurrent.later.andThen
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

abstract class SmeBusinessFormScene(private val options: SmeSceneOption<XSmeScheme>) : FormScene<SmeBusinessFields>() {
    protected fun form(output: SmeBusinessOutput, details: String) = SmeBusinessFields(output).toForm(
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