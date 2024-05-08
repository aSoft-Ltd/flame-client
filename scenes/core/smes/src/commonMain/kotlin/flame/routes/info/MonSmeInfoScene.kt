@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.info

import flame.MonSmeScheme
import flame.SmeProgress
import flame.SmeSceneOptions
import flame.transformers.toProgress
import kase.Loading
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.zip
import kotlinx.JsExport

class MonSmeInfoScene(private val options: SmeSceneOptions<MonSmeScheme>) : SmeInfoScene() {
    fun initialize(uid: String): Later<SmeProgress> {
        ui.value = Loading("loading information for business with uid = $uid, please wait...")
        return options.api.load(uid)
            .zip(options.auth.session()) { (it,session) ->
            it.toProgress(options.toAttachmentOptions(session))
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}