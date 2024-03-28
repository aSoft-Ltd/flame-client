@file:JsExport

package flame.routes.documents.utils

import cabinet.Attachment
import cabinet.FileUploadParam
import cinematic.Watcher
import cinematic.mutableLiveOf
import epsilon.FileField
import epsilon.RawFile
import epsilon.RawFileInfo
import flame.SmeSectionProgress
import flame.workers.SmeUploadDocumentWorker
import kase.progress.ProgressState
import kollections.get
import kotlinx.JsExport
import krest.params.SubmitWorkOptions

class SmeDocumentScene(internal val options: SmeDocumentSceneOptions) {

    val label by lazy {
        options.document.label.replace("-"," ").replaceFirstChar { it.uppercaseChar() }
    }

    val state = mutableLiveOf<SmeDocumentState>(SmeDocumentUninitialized)

    private var watcher: Watcher? = null

    internal var onSuccess: () -> Unit = {}
    internal fun initialize(attachment: Attachment?, onSuccess: () -> Unit) {
        this.onSuccess = onSuccess
        state.value = if (attachment != null) {
            SmeDocumentUploaded(attachment)
        } else {
            SmeDocumentNotUploaded
        }
        startWorkerWatcher()
    }

    val field = FileField(
        name = options.document.label,
        onChange = { upload(it) }
    )

    fun upload(file: RawFile?) {
        if (file == null) return
        val info = RawFileInfo(file)
        val swo = SubmitWorkOptions(
            type = SmeUploadDocumentWorker.TYPE,
            topic = options.topic,
            name = options.document.label,
            params = FileUploadParam(
                path = options.path,
                filename = "${options.document.label}.${info.extension}",
                file = file
            )
        )
        options.wm.submit(swo)
        stopWorkerWatcher()
        startWorkerWatcher()
    }

    private fun startWorkerWatcher() {
        if (!options.wm.hasWorkScheduled(options.type, options.topic)) return
        watcher = options.wm.liveWorkProgress(options.type, options.topic).watchEagerly {
            val p = it[options.document.label] ?: ProgressState.initial()
            state.value = SmeDocumentUploadingProgress(SmeSectionProgress(p.donePercentage.toInt(), 100))
        }
    }

    private fun stopWorkerWatcher() {
        watcher?.stop()
        watcher = null
    }

    internal fun deInitialize() {
        stopWorkerWatcher()
        state.value = SmeDocumentUninitialized
    }
}