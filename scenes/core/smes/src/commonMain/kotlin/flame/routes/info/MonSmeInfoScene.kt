@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.info

import flame.*
import flame.transformers.toPresenter
import kase.Loading
import kase.Success
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import koncurrent.later.zip
import kotlinx.JsExport

class MonSmeInfoScene(private val options: SmeSceneOptions<MonSmeScheme>) : SmeInfoScene() {
    fun initialize(uid: String): Later<SmeProgress> {
        ui.value = Loading("loading information for business with uid = $uid, please wait...")
        return options.api.load(uid).zip(options.auth.session()) { (it,session) ->
            info.value = Success(SmeInfoPresenter(it.toPresenter(options.toAttachmentOptions(session)), it.toProgress()))
            it.toProgress()
        }.finally {
            ui.value = it.toLazyState()
        }
    }
}