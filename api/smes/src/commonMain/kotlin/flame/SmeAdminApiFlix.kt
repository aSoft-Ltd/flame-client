package flame

import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import kase.response.getOrThrow
import keep.load
import koncurrent.later
import koncurrent.later.await
import kotlinx.serialization.encodeToString
import sentinel.UserSession

class SmeAdminApiFlix(private val options: SmeApiFlixOptions) : SmeAdminApi {

    private val logger by options.logger

    private fun update(key: String, params: String) = options.scope.later {
        val tracer = logger.trace(options.message.save(key))
        val res = options.http.get(options.routes.save(key)) {
            bearerAuth(options.cache.load<UserSession>(options.sessionCacheKey).await().secret)
            setBody(params)
        }
        res.getOrThrow<SmeDto>(options.codec, tracer)
    }

    override fun update(params: SmeContactsDto) = update("contacts", options.codec.encodeToString(params))

    override fun update(params: SmeBusinessDto) = update("business", options.codec.encodeToString(params))

    override fun update(params: SmeLegalComplianceDto) = update("legal", options.codec.encodeToString(params))
}