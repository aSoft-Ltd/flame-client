@file:JsExport

package flame.routes.financial.analysis

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.transformers.toPresenter
import kase.Loading
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.later.zip
import kotlinx.JsExport

class OwnFinancialAnalysisScene(
    private val options: SmeSceneOptions<OwnSmeScheme>
) : FinancialAnalysisScene(options) {
    override val api = options.api

    fun initialize() {
        ui.value = Loading("Fetching Financial Reports")
        options.auth.session().zip(api.load()) { (session, sme) ->
            sme.toPresenter(options.toAttachmentOptions(session))
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}