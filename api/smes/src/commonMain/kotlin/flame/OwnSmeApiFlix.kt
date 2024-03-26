package flame

import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.header
import kase.response.getOrThrow
import keep.load
import koncurrent.Later
import koncurrent.TODOLater
import koncurrent.later
import koncurrent.later.await
import sentinel.UserSession

class OwnSmeApiFlix(private val options: SmeApiFlixOptions) : OwnSmeScheme {

    private val logger by options.logger

    override fun load(): Later<SmeDto> = options.scope.later {
        val tracer = logger.trace(options.message.load())
        val res = options.http.get(options.routes.load()) {
            header(options.resolver,options.domain)
            bearerAuth(options.cache.load<UserSession>(options.sessionCacheKey).await().secret)
        }
        res.getOrThrow<SmeDto>(options.codec,tracer)
    }

    override fun update(sme: SmeDto): Later<SmeDto> = TODOLater()
}