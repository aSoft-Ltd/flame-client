@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package flame.routes.admin.contacts

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
import kotlin.js.JsExport
import symphony.toForm

class SmeContactFormScene(
    private val options: SmeSceneOption<SmeApi>
) : FormScene<SmeContactFields>() {
    fun initialize() {
        ui.value = Loading("loading your information, please wait . . .")
        options.api.load().then {
            it.admin?.contacts.toOutput()
        }.then {
            form(it)
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
                options.api.admin.update(it)
            }
        }
    }
}