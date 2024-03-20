package flame

import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.header
import kase.response.getOrThrow
import keep.load
import koncurrent.Later
import koncurrent.later
import koncurrent.later.await
import sentinel.UserSession

class SmeApiFlix(private val options: SmeApiFlixOptions) : SmeApi {

    override val admin by lazy { SmeAdminApiFlix(options) }

    override val funding by lazy { SmeFundingApiFlix(options) }

    override val finance by lazy { SmeFinanceApiFlix(options) }

    override val documents by lazy { SmeDocumentsApiFlix(options) }

    override val governance by lazy { SmeGovernanceApiFlix(options) }

    override val swot by lazy { SmeSwotApiFlix(options) }

    private val logger by options.logger

    override fun load(): Later<SmeDto> = options.scope.later {
        val tracer = logger.trace(options.message.load())
        val res = options.http.get(options.routes.load()) {
            header(options.resolver,options.domain)
            bearerAuth(options.cache.load<UserSession>(options.sessionCacheKey).await().secret)
        }
        res.getOrThrow<SmeDto>(options.codec,tracer)
    }
}