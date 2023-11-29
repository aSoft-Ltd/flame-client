package flame

import krest.WorkManager
import lexi.LoggerFactory
import sanity.EventBus

class SmeSceneOption<out A>(
    val api: A,
    val logger: LoggerFactory,
    val wm: WorkManager,
    val topic: SmeEventTopic,
    val bus: EventBus
) {
    fun <R> map(transform: (A) -> R) = SmeSceneOption(transform(api), logger, wm, topic, bus)
}