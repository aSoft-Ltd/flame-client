@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.route.admin.legal

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.transformers.admin.toLegalOutput
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class OwnSmeLegalFormScene(
    private val options: SmeSceneOption<OwnSmeScheme>,
) : SmeLegalFormScene(options) {
    fun initialize() : Later<Any> {
        ui.value = Loading("Loading business information")
        return options.api.load().then {
            form(it.toLegalOutput(), "Enter your legal information here")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}