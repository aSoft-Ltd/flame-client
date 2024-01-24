package flame

import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import kase.response.getOrThrow
import keep.load
import kollections.List
import kollections.ListSerializer
import koncurrent.Later
import koncurrent.later
import koncurrent.later.await
import kronecker.LoadOptions
import sentinel.UserSession

class SmesApiFlix(private val config: SmeApiFlixOptions) : SmesApi {

    private val logger by config.logger
    override fun load(options: LoadOptions): Later<List<SmeDto>> = config.scope.later {
        val trace = logger.trace(config.message.smes())
        val secret = config.cache.load(config.sessionCacheKey, UserSession.serializer()).await().secret
        config.http.get(config.routes.smes()) {
            bearerAuth(secret)
        }.getOrThrow(ListSerializer(SmeDto.serializer()), config.codec, trace)
    }
}