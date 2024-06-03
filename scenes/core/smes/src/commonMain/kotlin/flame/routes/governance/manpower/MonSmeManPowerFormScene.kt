@file:JsExport

package flame.routes.governance.manpower

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.transformers.governance.toManPowerOutput
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class MonSmeManPowerFormScene(
    private val options: SmeSceneOptions<MonSmeScheme>,
) : SmeManPowerFormScene(options) {
    fun initialize(uid: String): Later<Any> {
        ui.value = Loading("Loading business information")
        return options.api.load(uid).then {
            val label = it.admin?.business?.name ?: "SME"
            form(it.toManPowerOutput(), "Enter manpower details for $label")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}