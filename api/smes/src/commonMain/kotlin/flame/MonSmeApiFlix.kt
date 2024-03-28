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

class MonSmeApiFlix(private val config: SmeApiFlixOptions) : MonSmeScheme {

    private val logger by config.logger

    override fun create(params: SmeBusinessDto): Later<SmeDto> = config.scope.later {
        val tracer = logger.trace(config.message.create())
        val secret = config.cache.load(config.sessionCacheKey, UserSession.serializer()).await().secret
        config.http.post(config.routes.create()) {
            bearerAuth(secret)
            setBody(config.codec.encodeToString(SmeBusinessDto.serializer(),params))
        }.getOrThrow(SmeDto.serializer(), config.codec, tracer)
    }

    override fun list(options: LoadOptions): Later<List<SmeDto>> = config.scope.later {
        val tracer = logger.trace(config.message.smes())
        val secret = config.cache.load(config.sessionCacheKey, UserSession.serializer()).await().secret
        config.http.get(config.routes.load()) {
            bearerAuth(secret)
        }.getOrThrow(ListSerializer(SmeDto.serializer()), config.codec, tracer)
    }

    override fun load(uid: String): Later<SmeDto> = config.scope.later{
        val tracer = logger.trace(config.message.load())
        val secret = config.cache.load(config.sessionCacheKey, UserSession.serializer()).await().secret
        config.http.get(config.routes.load(uid)) {
            bearerAuth(secret)
        }.getOrThrow(SmeDto.serializer(), config.codec, tracer)
    }

    override fun update(sme: SmeDto): Later<SmeDto> = config.scope.later {
        val tracer = logger.trace(config.message.update())
        val secret = config.cache.load(config.sessionCacheKey, UserSession.serializer()).await().secret
        config.http.patch(config.routes.update()) {
            bearerAuth(secret)
            setBody(config.codec.encodeToString(SmeDto.serializer(),sme))
        }.getOrThrow(SmeDto.serializer(), config.codec, tracer)
    }
}