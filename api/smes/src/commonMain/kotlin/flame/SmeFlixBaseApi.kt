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

    protected fun upload(params: FileUploadParam) = options.scope.later { task ->
        val tracer = logger.trace("Uploading ${params.filename}")
        val secret = options.cache.load<UserSession>(options.sessionCacheKey).await().secret
        val (reading, uploading, releasing) = task.setStages("reading", "uploading", "releasing")
        val bytes = options.reader.read(params.file).await { p -> task.updateProgress(reading(p)) }
        val res = options.http.post(options.routes.documents()) {
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
        releasing(50, 100)
        res.getOrThrow<SmeDto>(options.codec, tracer).also { task.updateProgress(releasing(100, 100)) }
    }
}