@file:JsExport

package flame.route.ratios.utils

import kollections.List
import kollections.add
import kollections.buildList
import kollections.map
import kollections.plus
import kotlinx.JsExport
import krono.Clock
import krono.currentInstant

data class SmeStatements<out T>(
    val previous: List<T>,
    val current: T,
    val future: List<T>
) {

    val all by lazy { (previous + current + future) }

    internal fun <R> map(transform: (T) -> R) = SmeStatements(
        previous = previous.map(transform),
        current = transform(current),
        future = future.map(transform)
    )

    internal companion object {
        private const val MAX_HISTORY = 3
        private const val MAX_FUTURES = 5
        fun get(
            clock: Clock,
            histories: Int = MAX_HISTORY,
            futures: Int = MAX_FUTURES
        ): SmeStatements<Int> {
            val current = clock.currentInstant().atSystemZone().year
            return SmeStatements(
                previous = buildList { repeat(histories) { add(current - (histories - it)) } },
                current = current,
                future = buildList { repeat(futures) { add(current + it + 1) } }
            )
        }
    }
}