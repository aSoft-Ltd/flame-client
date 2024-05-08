@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.forms

import flame.MonSmeScheme
import flame.SmeDto
import flame.SmePresenter
import flame.SmeSceneOptions
import kase.Loading
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport
import symphony.Fields
import symphony.Form

class MonSmeFormScene<out F : Fields<*>>(
    private val options: SmeSceneOptions<MonSmeScheme>,
    private val initializer: (SmeDto) -> Form<SmePresenter, *, F>
) : XSmeFormScene<F>() {
    fun initialize(uid: String) {
        ui.value = Loading("Loading form please wait")
        options.api.load(uid).then {
            initializer(it)
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}