@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes

import cinematic.LazyScene
import flame.SmeMonitorApi
import flame.SmeProgress
import flame.SmeSceneOption
import flame.transformers.toProgress
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class SmeScene(private val options: SmeSceneOption<SmeMonitorApi>) : LazyScene<SmeProgress>() {

    fun initialize(uid: String) : Later<SmeProgress> {
        ui.value = Loading("loading sme with uid = $uid, please wait...")
        return options.api.load(uid).then {
            it.toProgress()
        }.finally { ui.value = it.toLazyState() }
    }
}