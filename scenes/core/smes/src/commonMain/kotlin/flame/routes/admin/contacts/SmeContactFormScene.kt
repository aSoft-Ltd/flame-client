@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package flame.routes.admin.contacts

import flame.SmeSceneOption
import flame.SmeScheme
import flame.forms.FormScene
import flame.forms.admin.contacts.SmeContactFields
import flame.forms.admin.contacts.SmeContactOutput
import flame.transformers.admin.toParams
import kase.Pending
import koncurrent.later.andThen
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

abstract class SmeContactFormScene(
    private val options: SmeSceneOption<SmeScheme>
) : FormScene<SmeContactFields>() {
    protected fun form(output: SmeContactOutput,details: String) = SmeContactFields(output).toForm(
        heading = "Contact Details",
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