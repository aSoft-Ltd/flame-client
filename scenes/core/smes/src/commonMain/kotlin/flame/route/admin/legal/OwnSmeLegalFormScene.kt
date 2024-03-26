@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.route.admin.legal

import flame.OwnSmeScheme
import flame.SmeApi
import flame.SmeSceneOption
import flame.forms.FormScene
import flame.forms.admin.legal.SmeLegalFields
import flame.forms.admin.legal.SmeLegalOutput
import flame.route.admin.directors.OwnSmeDirectorsScene
import flame.transformers.admin.toOutput
import flame.transformers.admin.toParams
import kase.Loading
import kase.Pending
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.toLater
import koncurrent.later.then
import koncurrent.later.andThen
import kotlinx.JsExport
import symphony.toForm

class OwnSmeLegalFormScene(
    private val options: SmeSceneOption<OwnSmeScheme>,
) : SmeLegalFormScene(options) {
    fun initialize() {
        ui.value = Loading("Loading business information")
        options.api.load().then {
            it.admin?.legal.toOutput()
        }.then {
            form(it, "Enter your legal information here")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}