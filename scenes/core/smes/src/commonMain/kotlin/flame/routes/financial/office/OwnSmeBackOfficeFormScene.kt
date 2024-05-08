@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.routes.financial.office

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.transformers.finance.toFinanceOfficeOutput
import kase.Loading
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class OwnSmeBackOfficeFormScene(
    private val options: SmeSceneOptions<OwnSmeScheme>,
) : SmeBackOfficeFormScene(options) {
    fun initialize() {
        ui.value = Loading("Loading business information")
        options.api.load().then {
            form(it.toFinanceOfficeOutput(), "Enter your business details here")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}