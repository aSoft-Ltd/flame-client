@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package flame.route.admin.contacts

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.forms.admin.contacts.SmeContactFields
import flame.forms.admin.contacts.SmeContactOutput
import flame.transformers.admin.toContactsOutput
import flame.transformers.admin.toParams
import kase.Loading
import kase.Pending
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

class OwnSmeContactFormScene(
    private val options: SmeSceneOption<OwnSmeScheme>
) : SmeContactFormScene() {
    fun initialize() : Later<Any> {
        ui.value = Loading("loading your information, please wait . . .")
        return options.api.load().then {
            form(it.toContactsOutput())
        }.finally {
            ui.value = it.toLazyState()
        }
    }

    private fun form(output: SmeContactOutput) = SmeContactFields(output).toForm(
        heading = "Contact Details",
        details = "Enter your contact details here",
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