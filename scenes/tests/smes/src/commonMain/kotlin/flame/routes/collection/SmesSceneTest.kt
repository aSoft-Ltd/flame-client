package flame.routes.collection

import kommander.expect
import koncurrent.later.await

abstract class SmesSceneTest(private val scene: SmesScene) {
    protected suspend fun initializationTest() {
        scene.initialize().await()
        val page = scene.paginator.loadFirstPage().await()
        expect(page.number).toBe(1)
    }
}