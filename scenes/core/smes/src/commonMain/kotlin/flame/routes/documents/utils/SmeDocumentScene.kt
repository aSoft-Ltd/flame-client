@file:JsExport

package flame.routes.documents.utils

import cabinet.AttachmentPresenter
import cabinet.FileUploadParam
import cinematic.Watcher
import cinematic.mutableLiveOf
import epsilon.FileField
import epsilon.RawFile
import epsilon.RawFileInfo
import flame.tasks.OwnSmeUploadDocumentTask
import kotlinx.JsExport
import krest.named
import krest.toSubmitOptions

class SmeDocumentScene(internal val options: SmeDocumentSceneOptions) {

    val label by lazy {
        options.document.label.replace("-", " ").replaceFirstChar { it.uppercaseChar() }
    }

    val state = mutableLiveOf<SmeDocumentState>(SmeDocumentUninitialized)

    private val runner by lazy { options.runner }

    private val task by lazy { OwnSmeUploadDocumentTask::class.named(options.document.label) }

    private var watcher: Watcher? = null

    internal var onSuccess: () -> Unit = {}

    internal fun initialize(attachment: AttachmentPresenter?, onSuccess: () -> Unit) {
        this.onSuccess = onSuccess
        state.value = if (attachment != null) {
            SmeDocumentUploaded(attachment)
        } else {
            SmeDocumentNotUploaded
        }
        startTaskWatcher()
    }

    val field = FileField(
        name = options.document.label,
        onChange = { upload(it?.file) }
    )

    fun upload(file: RawFile?) {
        if (file == null || runner.isRunning(task)) return

        val info = RawFileInfo(file)
        val params = FileUploadParam(
            path = options.path,
            filename = "${options.document.label}.${info.extension}",
            file = file
        )
        val tso = task.toSubmitOptions(params)
        runner.submit(tso)
        stopTaskWatcher()
        startTaskWatcher()
    }

    private fun startTaskWatcher() {
        if (runner.isRunning(task)) runner.watch(task) {
            state.value = SmeDocumentUploadingProgress(it)
            if(it.overall.percentage.done>99.5) {
                stopTaskWatcher()
                onSuccess()
            }
        }
    }

    private fun stopTaskWatcher() {
        watcher?.stop()
        watcher = null
    }

    internal fun deInitialize() {
        stopTaskWatcher()
        state.value = SmeDocumentUninitialized
    }
}