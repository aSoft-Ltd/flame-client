@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package flame.routes.admin.contacts

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.transformers.admin.toContactsOutput
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class MonSmeContactFormScene(
    private val options: SmeSceneOptions<MonSmeScheme>,
) : SmeContactFormScene(options) {

    fun initialize(uid: String): Later<Any> {
        ui.value = Loading("Loading admin contact information for business with uid = $uid")
        return options.api.load(uid).then {
            form(it.toContactsOutput(),"Enter ${it.admin?.business?.name ?: "SME"} contact details here")
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}