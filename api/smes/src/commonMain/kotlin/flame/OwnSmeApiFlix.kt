package flame

import cabinet.Attachment
import cabinet.FileUploadParam
import epsilon.MemorySize
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.escapeIfNeeded
import io.ktor.http.headersOf
import kase.progress.ProgressBus
import kase.progress.Progression
import kase.progress.SimpleProgression
import kase.response.getOrThrow
import keep.load
import kollections.component1
import kollections.component2
import koncurrent.Later
import koncurrent.later
import koncurrent.later.await
import koncurrent.later.estimate
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import sentinel.UserSession

class OwnSmeApiFlix(private val options: OwnSmeApiFlixOptions) : OwnSmeApi {

    private val logger by options.logger
    private val reader = options.reader

    override fun load(): Later<SmeDto> = options.scope.later {
        val tracer = logger.trace(options.message.load())
        val res = options.http.get(options.routes.load()) {
            header(options.resolver, options.domain)
            bearerAuth(options.cache.load<UserSession>(options.sessionCacheKey).await().secret)
        }
        res.getOrThrow<SmeDto>(options.codec, tracer)
    }

    override fun update(sme: SmeDto): Later<SmeDto> = options.scope.later {
        val tracer = logger.trace(options.message.update())
        val secret = options.cache.load(options.sessionCacheKey, UserSession.serializer()).await().secret
        options.http.patch(options.routes.update()) {
            header(options.resolver, options.domain)
            bearerAuth(secret)
            setBody(options.codec.encodeToString(SmeDto.serializer(), sme))
        }.getOrThrow(SmeDto.serializer(), options.codec, tracer)
    }

    override fun upload(params: FileUploadParam, onProgress: ((SimpleProgression<MemorySize>) -> Unit)?): Later<Attachment> = options.scope.later {
        val tracer = logger.trace(options.message.upload(params.filename))
        val secret = options.cache.load(options.sessionCacheKey, UserSession.serializer()).await().secret
        val (reading, uploading) = Progression<MemorySize>("Reading", "Uploading")
        val (reading, uploading) = progress.setStages("Reading", "Uploading")
        val bytes = reader.read(params.file).await { progress.updateProgress(reading(it)) }
        val size = bytes.size
        val data = MultiPartFormDataContent(formData {
            append("file", bytes, headersOf(HttpHeaders.ContentDisposition, "filename=${params.filename.escapeIfNeeded()}"))
        })
        coroutineScope {
            var completed = false

            val uploader = async {
                options.http.post(options.routes.upload(params.filename)) {
                    header(options.resolver, options.domain)
                    bearerAuth(secret)
                    setBody(data)
                }.also { completed = true }
            }

            val estimator = async {
                estimate(size) { completed }.await { progress.updateProgress(uploading(it)) }
            }

            estimator.await()
            val resp = uploader.await()
            progress.updateProgress(uploading(size, size))
            resp.getOrThrow(Attachment.serializer(), options.codec, tracer)
        }
    }
}