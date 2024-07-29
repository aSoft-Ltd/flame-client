@file:JsExport

package flame.routes.financial.analysis

import cabinet.FileUploadParam
import cinematic.MutableLive
import cinematic.mutableLiveOf
import epsilon.RawFile
import epsilon.RawFileInfo
import flame.OwnSmeApi
import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.documents.SmeDocument
import flame.transformers.toPresenter
import kase.Loading
import kase.toLazyState
import koncurrent.later.catch
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.later.zip
import kotlinx.JsExport
import krest.toSubmitOptions

class SpreadsheetUploader(
    private val options: SmeSceneOptions<OwnSmeApi>,
    private val onUpload: () -> Unit
) {
    private val api = options.api
    val state:MutableLive<State> = mutableLiveOf(State.Blank)

    fun upload(file: RawFile?) {
        file?.let { file->
            val document = SmeDocument.FinancialSpreadsheet()
            val info = RawFileInfo(file)
            val params = FileUploadParam(
                path = "xlsx",
                filename = "${document.label}.${info.extension}",
                file = file
            )
            state.value = State.Uploading
            api.xlsx(params).then {
                state.value = State.Uploaded
                onUpload()
            }.catch {
                state.value = State.Error(it.message ?: "Unknown error uploading!")
            }
        }?: run {
            state.value = State.Error("No file selected")
        }
    }

    sealed class State {
        object Blank:State()
        object Uploading:State()
        object Uploaded:State()
        class Error(val message:String):State()

        val blank get() = this as? Blank
        val uploading get() = this as? Uploading
        val uploaded get() = this as? Uploaded
        val error get() = this as? Error
    }
}