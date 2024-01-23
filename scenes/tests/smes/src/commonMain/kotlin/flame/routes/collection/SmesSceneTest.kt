package flame.routes.collection

import kase.Success
import kommander.expect
import kommander.toBe
import koncurrent.later.await
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

abstract class SmesSceneTest(private val scene: SmesScene) {
    protected suspend fun initializationTest() {
        scene.initialize().await()
        expect(scene.ui.value).toBe<Success<*>>()
    }
}