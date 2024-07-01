@file:JsExport

package flame.routes.financial.analysis

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.transformers.toPresenter
import kase.Loading
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.later.zip
import kotlinx.JsExport

class MonFinancialAnalysisScene(
    private val options: SmeSceneOptions<MonSmeScheme>
) : FinancialAnalysisScene(options) {

    override val api = options.api

    fun initialize(uid: String) {
        ui.value = Loading("Fetching Financial Reports")
        options.auth.session().zip(api.load(uid)) { (session, sme) ->
            sme.toPresenter(options.toAttachmentOptions(session))
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}