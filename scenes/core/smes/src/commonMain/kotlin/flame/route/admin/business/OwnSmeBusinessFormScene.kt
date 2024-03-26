@file:JsExport

package flame.route.admin.business

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.transformers.admin.toBusinessOutput
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class OwnSmeBusinessFormScene(
    private val options: SmeSceneOption<OwnSmeScheme>,
) : SmeBusinessFormScene(options) {
    fun initialize(): Later<Any> {
        ui.value = Loading("Loading business information")
        return options.api.load().then {
            form(it.toBusinessOutput(), "Enter your business details here")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}