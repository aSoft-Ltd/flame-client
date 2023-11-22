package flame.routes

import cinematic.LazyScene
import flame.SmeApi
import flame.SmeDto
import flame.SmeSceneOption
import kase.Loading
import kase.toLazyState
import koncurrent.later.finally

class SmeInfoScene(private val options: SmeSceneOption<SmeApi>) : LazyScene<SmeDto>() {
    fun initialize() {
        ui.value = Loading("loading your information, please wait...")
        options.api.load().finally { ui.value = it.toLazyState() }
    }
}