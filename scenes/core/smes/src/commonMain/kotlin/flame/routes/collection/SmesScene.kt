@file:JsExport

package flame.routes.collection

import cinematic.LazyScene
import flame.SmePresenter
import flame.SmeSceneOption
import flame.SmeMonitorApi
import flame.transformers.toPresenter
import kase.Loading
import kase.toLazyState
import kollections.List
import kollections.map
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class SmesScene(private val options: SmeSceneOption<SmeMonitorApi>) : LazyScene<List<SmePresenter>>() {
    fun initialize(): Later<List<SmePresenter>> {
        ui.value = Loading("Fetching a list of all businesses. Please wait. . .")
        return options.api.list().then { smes ->
            smes.map { it.toPresenter() }
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}