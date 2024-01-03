package flame.routes.ratios

import flame.routes.ratios.pnl.SmePnLScene
import kollections.size
import kommander.expect
import koncurrent.Later
import koncurrent.later.await
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.fail

abstract class SmePnLSceneTest(private val scene: Later<SmePnLScene>) {
    @Test
    fun should_initialize_into_a_form() = runTest {
        val s = scene.await()
        s.initialize()
        expect(s.statements.value.data?.all?.size).toBe(9)
    }

    @Test
    fun should_be_able_to_render_mutable_structured_inputs() = runTest {
        val s = scene.await()
        s.initialize()
        expect(s.statements.value.data?.all?.size).toBe(9)
        TODO("Finish this implementation please")
    }
}