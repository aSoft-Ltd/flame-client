@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.route.info

import flame.MonSmeScheme
import flame.SmeProgress
import flame.SmeSceneOption
import flame.transformers.toProgress
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.then
import kotlinx.JsExport

class MonSmeInfoScene(private val options: SmeSceneOption<MonSmeScheme>) : SmeInfoScene2() {
    fun initialize(uid: String): Later<SmeProgress> {
        ui.value = Loading("loading information for business with uid = $uid, please wait...")
        return options.api.load(uid).then {
            it.toProgress()
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}