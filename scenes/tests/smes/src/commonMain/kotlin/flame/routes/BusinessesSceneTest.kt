package flame.routes

import kase.Success
import kommander.expect
import kommander.toBe
import koncurrent.Later
import koncurrent.later.await
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

abstract class BusinessesSceneTest(private val scene: Later<BusinessesScene>) {
    @Test
    fun initializing_the_scene_should_load_all_businesses() = runTest {
        val s = scene.await()
        s.initialize().await()
        expect(s.ui.value).toBe<Success<*>>()
    }
}