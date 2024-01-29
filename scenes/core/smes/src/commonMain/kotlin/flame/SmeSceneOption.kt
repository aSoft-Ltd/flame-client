package flame

import keep.Cache
import keep.Cacheable
import krest.WorkManager
import krono.Clock
import lexi.LoggerFactory
import sanity.EventBus

class SmeSceneOption<out A>(
    val api: A,
    val logger: LoggerFactory,
    val wm: WorkManager,
    val topic: SmeEventTopic,
    val bus: EventBus,
    val clock: Clock,
    override val cache: Cache
) : Cacheable {
    fun <R> map(transform: (A) -> R) = SmeSceneOption(transform(api), logger, wm, topic, bus, clock, cache)
}