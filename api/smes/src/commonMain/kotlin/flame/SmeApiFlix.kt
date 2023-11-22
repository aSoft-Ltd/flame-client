package flame

import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import kase.response.getOrThrow
import keep.load
import koncurrent.Later
import koncurrent.later
import koncurrent.later.await
import sentinel.UserSession

class SmeApiFlix(private val options: SmeApiFlixOptions) : SmeApi {

    override val admin by lazy { SmeAdminApiFlix(options) }

    override val funding by lazy { SmeFundingApiFlix(options) }

    private val logger by options.logger

    override fun load(): Later<SmeDto> = options.scope.later {
        val tracer = logger.trace(options.message.load())
        val res = options.http.get(options.routes.load()) {
            bearerAuth(options.cache.load<UserSession>(options.sessionCacheKey).await().secret)
        }
        res.getOrThrow<SmeDto>(options.codec,tracer)
    }
}