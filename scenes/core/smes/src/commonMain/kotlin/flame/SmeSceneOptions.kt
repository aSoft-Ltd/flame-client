package flame

import cabinet.AttachmentPresenterOptions
import epsilon.FileManager
import io.ktor.client.HttpClient
import keep.Cache
import keep.Cacheable
import klip.Clipboard
import kotlinx.coroutines.CoroutineScope
import krest.WorkManager
import krono.Clock
import lexi.LoggerFactory
import sanity.EventBus
import sentinel.AuthenticationApi
import sentinel.Resolver
import sentinel.UserSession

class SmeSceneOptions<out A>(
    val auth: AuthenticationApi,
    val api: A,
    val logger: LoggerFactory,
    val wm: WorkManager,
    val topic: SmeEventTopic,
    val bus: EventBus,
    val clock: Clock,
    val scope: CoroutineScope,
    val files: FileManager,
    val http: HttpClient,
    val clipboard: Clipboard,
    val resolver: Resolver?,
    override val cache: Cache
) : Cacheable {
    fun <R> map(transform: (A) -> R) = SmeSceneOptions(auth, transform(api), logger, wm, topic, bus, clock, scope, files, http, clipboard, resolver, cache)

    fun toAttachmentOptions(session: UserSession) = AttachmentPresenterOptions(headers = buildMap {
        put("Authorization", "Bearer ${session.secret}")
        resolver?.let { put(it.key, it.value ?: "") }
    }, scope, http, clipboard, files)
}