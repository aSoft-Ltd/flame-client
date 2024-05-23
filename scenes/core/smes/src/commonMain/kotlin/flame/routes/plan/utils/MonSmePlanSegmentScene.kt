@file:JsExport

package flame.routes.plan.utils

import flame.MonSmeScheme
import flame.SmeSceneOptions
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

abstract class MonSmePlanSegmentScene(private val options: SmeSceneOptions<MonSmeScheme>) : SmePlanSegmentScene() {

    protected val api = options.api

    fun initialize(uid: String): Later<Any> {
        ui.value = Loading("Loading information, please wait...")
        return api.load(uid).then { form(it) }.finally {
            ui.value = it.toLazyState()
        }
    }
}