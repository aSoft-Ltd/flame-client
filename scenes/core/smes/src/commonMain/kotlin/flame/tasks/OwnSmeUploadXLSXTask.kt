package flame.tasks

import cabinet.FileUploadParam
import epsilon.MemorySize
import flame.OwnSmeApi
import flame.SmeSceneOptions
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import krest.StagedProgressTask
import status.StagedProgressTracker

class OwnSmeUploadXLSXTask(private val options: SmeSceneOptions<OwnSmeApi>) : StagedProgressTask<FileUploadParam, MemorySize>() {
    override fun execute(params: FileUploadParam): Later<Unit> {
        val tracker = StagedProgressTracker<MemorySize>()
        val listener = tracker.onProgress { update(it) }
        return options.api.xlsx(params, tracker).then { }.finally { tracker.removeProgressListener(listener) }
    }
}