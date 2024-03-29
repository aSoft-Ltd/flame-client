package flame

import epsilon.FileReader
import io.ktor.client.HttpClient
import keep.Cache
import lexi.LoggerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.StringFormat

class MonSmeApiFlixOptions(
    val scope: CoroutineScope,
    val cache: Cache,
    val logger: LoggerFactory,
    val resolver: String,
    val domain: String,
    val http: HttpClient,
    val routes: MonSmeReference,
    val sessionCacheKey: String,
    val message: MonSmeReference,
    val codec: StringFormat,
    val reader: FileReader
)