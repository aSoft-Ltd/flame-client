package flame

import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import kase.response.getOrThrow
import keep.load
import koncurrent.Later
import koncurrent.later
import koncurrent.later.await
import sentinel.UserSession

class OwnSmeApiFlix(private val options: OwnSmeApiFlixOptions) : OwnSmeScheme {

    private val logger by options.logger

    override fun load(): Later<SmeDto> = options.scope.later {
        val tracer = logger.trace(options.message.load())
        val res = options.http.get(options.routes.load()) {
            header(options.resolver,options.domain)
            bearerAuth(options.cache.load<UserSession>(options.sessionCacheKey).await().secret)
        }
        res.getOrThrow<SmeDto>(options.codec,tracer)
    }

    override fun update(sme: SmeDto): Later<SmeDto> = options.scope.later {
        val tracer = logger.trace(options.message.update())
        val secret = options.cache.load(options.sessionCacheKey, UserSession.serializer()).await().secret
        options.http.patch(options.routes.update()) {
            header(options.resolver,options.domain)
            bearerAuth(secret)
            setBody(options.codec.encodeToString(SmeDto.serializer(),sme))
        }.getOrThrow(SmeDto.serializer(), options.codec, tracer)
    }
}