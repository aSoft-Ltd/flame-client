@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package flame.routes.admin.contacts

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.transformers.admin.toContactsOutput
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class OwnSmeContactFormScene(
    private val options: SmeSceneOption<OwnSmeScheme>,
) : SmeContactFormScene(options) {
    fun initialize(): Later<Any> {
        ui.value = Loading("loading your information, please wait . . .")
        return options.api.load().then {
            form(it.toContactsOutput(), "Enter your contact details here")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}