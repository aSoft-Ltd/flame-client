@file:JsExport

package flame.routes.governance

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.transformers.governance.toManPowerOutput
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class OwnSmeGovernanceFormScene(
    private val options: SmeSceneOptions<OwnSmeScheme>,
) : SmeGovernanceFormScene(options) {
    fun initialize(): Later<Any> {
        ui.value = Loading("Loading business information")
        return options.api.load().then {
            form(it.toManPowerOutput(), "Enter your governance details here")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}