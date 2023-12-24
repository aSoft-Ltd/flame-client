@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.utils

import cinematic.LazyScene
import flame.SmeApi
import flame.SmeDto
import flame.SmePresenter
import flame.SmeSceneOption
import kase.Loading
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport
import symphony.Fields
import symphony.Form

class SmeFormScene<out F : Fields<*>>(
    private val options: SmeSceneOption<SmeApi>,
    private val initializer: (SmeDto) -> Form<SmePresenter, *, F>
) : LazyScene<Form<SmePresenter, *, F>>() {
    fun initialize() {
        ui.value = Loading("Loading form please wait")
        options.api.load().then {
            initializer(it)
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}

fun <F : Fields<*>> SmeSceneOption<SmeApi>.toSmeScene(
    initializer: (SmeDto) -> Form<SmePresenter, *, F>
) = SmeFormScene(this, initializer)