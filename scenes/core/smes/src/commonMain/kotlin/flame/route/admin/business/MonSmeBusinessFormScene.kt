@file:JsExport

package flame.route.admin.business

import flame.MonSmeScheme
import flame.SmeSceneOption
import flame.forms.admin.business.SmeBusinessFields
import flame.forms.admin.business.SmeBusinessOutput
import flame.transformers.admin.toBusinessOutput
import flame.transformers.admin.toParams
import kase.Loading
import kase.Pending
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

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