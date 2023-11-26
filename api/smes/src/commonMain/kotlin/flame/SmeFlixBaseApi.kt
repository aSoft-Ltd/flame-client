package flame

import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kase.response.getOrThrow
import keep.load
import koncurrent.later
import koncurrent.later.await
import sentinel.UserSession

abstract class SmeFlixBaseApi(protected val options: SmeApiFlixOptions) {

    protected val logger by options.logger
    protected fun update(key: SmeKey, params: String) = options.scope.later {
        val tracer = logger.trace(options.message.save(key))
        val res = options.http.post(options.routes.save(key)) {
            bearerAuth(options.cache.load<UserSession>(options.sessionCacheKey).await().secret)
            setBody(params)
        }
        res.getOrThrow<SmeDto>(options.codec, tracer)
    }
}