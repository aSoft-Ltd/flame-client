@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.routes.admin.legal

import flame.MonSmeScheme
import flame.SmeSceneOption
import flame.transformers.admin.toLegalOutput
import kase.Loading
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class MonSmeLegalFormScene(
    private val options: SmeSceneOption<MonSmeScheme>,
) : SmeLegalFormScene(options) {
    fun initialize(uid: String) {
        ui.value = Loading("Loading information for business (uid = $uid)")
        options.api.load(uid).then {
            form(it.toLegalOutput(), "Enter your legal information here")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}