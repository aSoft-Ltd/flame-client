@file:JsExport

package flame.routes.plan.utils

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

abstract class OwnSmePlanSegmentScene(private val options: SmeSceneOptions<OwnSmeScheme>) : SmePlanSegmentScene() {

    protected val api = options.api

    fun initialize(): Later<Any> {
        ui.value = Loading("Loading information, please wait...")
        return api.load().then { form(it) }.finally {
            ui.value = it.toLazyState()
        }
    }
}