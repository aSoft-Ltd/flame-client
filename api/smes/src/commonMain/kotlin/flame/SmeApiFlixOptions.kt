package flame

import epsilon.FileReader
import io.ktor.client.HttpClient
import keep.Cache
import lexi.LoggerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.StringFormat

class SmeApiFlixOptions(
    val scope: CoroutineScope,
    val cache: Cache,
    val logger: LoggerFactory,
    val resolver: String,
    val domain: String,
    val http: HttpClient,
    val routes: SmeEndpoint,
    val sessionCacheKey: String,
    val codec: StringFormat,
    val reader: FileReader
) {
    val message by lazy { SmeActionMessage() }
}