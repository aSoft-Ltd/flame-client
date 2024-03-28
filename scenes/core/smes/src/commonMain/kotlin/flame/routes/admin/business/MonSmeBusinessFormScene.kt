@file:JsExport

package flame.routes.admin.business

import flame.MonSmeScheme
import flame.SmeSceneOption
import flame.transformers.admin.toBusinessOutput
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class MonSmeBusinessFormScene(
    private val options: SmeSceneOption<MonSmeScheme>,
) : SmeBusinessFormScene(options) {
    fun initialize(uid: String): Later<Any> {
        ui.value = Loading("Loading admin business information for business with uid = $uid")
        return options.api.load(uid).then {
            form(it.toBusinessOutput(), "Enter ${it.admin?.business?.name ?: "SME"} business details here")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}