package flame.routes

import cinematic.LazyScene
import flame.SmeApi
import flame.SmeDto
import flame.SmeProgress
import flame.SmeSceneOption
import flame.transformers.toProgress
import kase.Loading
import kase.toLazyState
import koncurrent.later.finally

class SmeInfoScene(private val options: SmeSceneOption<SmeApi>) : LazyScene<SmeProgress>() {
    fun initialize() {
        ui.value = Loading("loading your information, please wait...")
        options.api.load().then {
            it.toProgress()
        }.finally { ui.value = it.toLazyState() }
    }
}