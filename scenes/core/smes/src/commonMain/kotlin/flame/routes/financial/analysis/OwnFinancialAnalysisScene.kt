@file:JsExport

package flame.routes.financial.analysis

import bringer.Downloader
import cabinet.AttachmentPresenter
import cabinet.FileUploadParam
import cinematic.MutableLive
import cinematic.mutableLiveOf
import epsilon.RawFile
import epsilon.RawFileInfo
import flame.OwnSmeApi
import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.documents.SmeDocument
import flame.transformers.spreadsheetTemplate
import flame.transformers.toPresenter
import kase.*
import koncurrent.later.*
import kotlinx.JsExport
import krest.toSubmitOptions

class OwnFinancialAnalysisScene(
    private val options: SmeSceneOptions<OwnSmeApi>
) : FinancialAnalysisScene(options) {
    override val api = options.api
    val uploader = SpreadsheetUploader(options) {
        println("Upload done. Reloading sme...")
        loadSme()
    }

    val xlsx:MutableLive<AttachmentPresenter?> = mutableLiveOf(null)

    fun initialize() {
        loadSme()
    }

    private fun loadSme() {
        ui.value = Loading("Fetching Financial Reports")
        options.auth.session().zip(api.load()) { (session, sme) ->
            sme.toPresenter(options.toAttachmentOptions(session))
        }.then {
            this.xlsx.value = it.xlsx
            it
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}