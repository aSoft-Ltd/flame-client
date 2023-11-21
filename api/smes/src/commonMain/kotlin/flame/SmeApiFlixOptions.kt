package flame

import io.ktor.client.HttpClient
import keep.Cache
import lexi.LoggerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.StringFormat

class SmeApiFlixOptions(
    val scope: CoroutineScope,
    val cache: Cache,
    val logger: LoggerFactory,
    val http: HttpClient,
    val routes: SmeRoutes,
    val sessionCacheKey: String,
    val codec: StringFormat
) {
    val message by lazy { SmeActionMessage() }
}