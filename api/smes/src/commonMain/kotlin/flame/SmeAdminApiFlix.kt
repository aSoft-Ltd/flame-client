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
    override fun update(params: SmeContactsDto) = options.scope.later {
        val tracer = logger.trace(options.message.saveContacts())
        val res = options.http.get(options.routes.saveContacts()) {
            bearerAuth(options.cache.load<UserSession>(options.sessionCacheKey).await().secret)
            setBody(options.codec.encodeToString(params))
        }
        res.getOrThrow<SmeDto>(options.codec, tracer)
    }
}