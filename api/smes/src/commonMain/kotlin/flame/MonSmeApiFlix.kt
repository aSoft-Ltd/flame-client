package flame

import flame.admin.SmeBusinessDto
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kase.response.getOrThrow
import kollections.List
import kollections.ListSerializer
import koncurrent.Later
import koncurrent.later
import koncurrent.later.await
import kronecker.LoadOptions
import sentinel.UserSession

class MonSmeApiFlix(private val options: MonSmeApiFlixOptions) : MonSmeScheme {

    private val logger by options.logger
    private val message = options.message
    private val codec = options.codec
    private val cache = options.cache
    private val http = options.http
    private val routes = options.routes

    private suspend fun secret() = cache.load(options.sessionCacheKey, UserSession.serializer()).await().secret

    override fun create(params: SmeBusinessDto): Later<SmeDto> = options.scope.later {
        val tracer = logger.trace(message.create())
        http.post(routes.create()) {
            bearerAuth(secret())
            setBody(codec.encodeToString(SmeBusinessDto.serializer(),params))
        }.getOrThrow(SmeDto.serializer(), codec, tracer)
    }

    override fun list(options: LoadOptions): Later<List<SmeDto>> = this.options.scope.later {
        val tracer = logger.trace(message.list())
        http.get(routes.list()) {
            bearerAuth(secret())
        }.getOrThrow(ListSerializer(SmeDto.serializer()), codec, tracer)
    }

    override fun load(uid: String): Later<SmeDto> = options.scope.later{
        val tracer = logger.trace(message.load(uid))
        http.get(routes.load(uid)) {
            bearerAuth(secret())
        }.getOrThrow(SmeDto.serializer(), codec, tracer)
    }

    override fun update(sme: SmeDto): Later<SmeDto> = options.scope.later {
        val tracer = logger.trace(message.update())
        http.patch(routes.update()) {
            bearerAuth(secret())
            setBody(codec.encodeToString(SmeDto.serializer(),sme))
        }.getOrThrow(SmeDto.serializer(), codec, tracer)
    }
}