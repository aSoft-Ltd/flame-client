package flame

import cabinet.FileUploadParam
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kase.response.getOrThrow
import keep.load
import kollections.component1
import kollections.component2
import kollections.component3
import koncurrent.later
import koncurrent.later.await
import koncurrent.later.estimate
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
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

    protected fun upload(params: FileUploadParam) = options.scope.later { task ->
        val tracer = logger.trace("Uploading ${params.filename}")
        val secret = options.cache.load<UserSession>(options.sessionCacheKey).await().secret
        val (reading, uploading, estimating) = task.setStages("reading", "uploading", "finalizing")
        val bytes = options.reader.read(params.file).await { p -> task.updateProgress(reading(p)) }
        val res = async {
            options.http.post(options.routes.documents()) {
                bearerAuth(secret)
                setBody(MultiPartFormDataContent(
                    formData {
                        append("path", params.path)
                        append("name", params.filename)
                        append("file", bytes, Headers.build {
                            append(HttpHeaders.ContentDisposition, "filename=\"${params.filename}\"")
                        })
                    }
                ))

                onUpload { bytesSentTotal, contentLength -> task.updateProgress(uploading(bytesSentTotal, contentLength)) }
            }
        }

        estimate(bytes.size, until = { res.isCompleted }).await { task.updateProgress(estimating(it)) }
        task.updateProgress(estimating(bytes.size, bytes.size))
        delay(500.milliseconds)

        res.await().getOrThrow<SmeDto>(options.codec, tracer)
    }
}