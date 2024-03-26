@file:JsExport

package flame.route.governance

import flame.MonSmeScheme
import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.transformers.admin.toBusinessOutput
import flame.transformers.governance.toGovernanceOutput
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class MonSmeGovernanceFormScene(
    private val options: SmeSceneOption<MonSmeScheme>,
) : SmeGovernanceFormScene(options) {
    fun initialize(uid: String): Later<Any> {
        ui.value = Loading("Loading business information")
        return options.api.load(uid).then {
            val label = it.admin?.business?.name ?: "SME"
            form(it.toGovernanceOutput(), "Enter governance details for $label")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}