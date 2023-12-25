@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes

import cinematic.LazyScene
import flame.SmeApi
import flame.SmeProgress
import flame.SmeSceneOption
import flame.transformers.toProgress
import kase.Loading
import kase.Success
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport
import sanity.Subscriber

class SmeInfoScene(private val options: SmeSceneOption<SmeApi>) : LazyScene<SmeProgress>() {

    private var subscriber: Subscriber? = null
    fun initialize() {
        ui.value = Loading("loading your information, please wait...")
        options.api.load().then {
            it.toProgress()
        }.finally { ui.value = it.toLazyState() }
        subscriber = options.bus.subscribe(options.topic.progressMade()) { refresh() }
    }

    fun refresh() = options.api.load().then {
        it.toProgress()
    }.then {
        ui.value = Success(it)
    }

    override fun deInitialize() {
        subscriber?.unsubscribe()
        subscriber = null
        super.deInitialize()
    }
}