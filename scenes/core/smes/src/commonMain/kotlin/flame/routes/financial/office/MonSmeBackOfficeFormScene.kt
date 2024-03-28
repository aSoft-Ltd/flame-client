@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.routes.financial.office

import flame.MonSmeScheme
import flame.SmeSceneOption
import flame.transformers.finance.toFinanceOfficeOutput
import kase.Loading
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class MonSmeBackOfficeFormScene(
    private val options: SmeSceneOption<MonSmeScheme>,
) : SmeBackOfficeFormScene(options) {
    fun initialize(uid: String) {
        ui.value = Loading("Loading information for business uid = $uid")
        options.api.load(uid).then {
            form(it.toFinanceOfficeOutput(), "Enter your business details here")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}