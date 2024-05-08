@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.routes.admin.legal

import flame.SmeSceneOptions
import flame.SmeScheme
import flame.forms.FormScene
import flame.forms.admin.legal.SmeLegalFields
import flame.forms.admin.legal.SmeLegalOutput
import flame.transformers.admin.toParams
import kase.Pending
import koncurrent.later.andThen
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

abstract class SmeLegalFormScene(private val options: SmeSceneOptions<SmeScheme>) : FormScene<SmeLegalFields>() {
    protected fun form(output: SmeLegalOutput, details: String) = SmeLegalFields(output).toForm(
        heading = "Legal Compliance Form",
        details = details,
        logger = options.logger,
    ) {
        onCancel { ui.value = Pending }
        onSubmit { output ->
            output.toLater().then {
                it.toParams()
            }.andThen {
                options.api.update(it)
            }
        }
        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}