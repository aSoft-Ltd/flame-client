@file:JsExport

package flame.routes.financial.analysis

import cabinet.FileUploadParam
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

class OwnFinancialAnalysisScene(
    private val options: SmeSceneOptions<OwnSmeApi>
) : FinancialAnalysisScene(options) {
    override val api = options.api
    val uploader = SpreadsheetUploader(options) {
        loadSme()
    }

    fun initialize() {
        loadSme()
    }

    private fun loadSme() {
        ui.value = Loading("Fetching Financial Reports")
        options.auth.session().zip(api.load()) { (session, sme) ->
            sme.toPresenter(options.toAttachmentOptions(session))
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}