package flame.routes

import cinematic.LazyScene
import flame.BusinessesApi
import flame.SmePresenter
import flame.SmeSceneOption
import kollections.List
import koncurrent.Later

class BusinessesScene(private val options: SmeSceneOption<BusinessesApi>) : LazyScene<List<SmePresenter>>() {
    fun initialize(): Later<List<SmePresenter>> {
        TODO()
    }
}