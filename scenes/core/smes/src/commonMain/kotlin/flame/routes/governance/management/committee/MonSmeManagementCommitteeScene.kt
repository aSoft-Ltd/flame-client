@file:JsExport

package flame.routes.governance.management.committee

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.transformers.toPresenter
import koncurrent.later.then
import koncurrent.later.zip
import kotlinx.JsExport

class MonSmeManagementCommitteeScene(private val options: SmeSceneOptions<MonSmeScheme>) : SmeManagementCommitteeScene(options) {
    private val api get() = options.api
    fun initialize(uid: String) = paginator.initialize {
        api.load(uid).zip(options.auth.session()) { (sme, session) ->
            sme.toPresenter(options.toAttachmentOptions(session))
        }.then {
            presenter = it
            it.toMember()
        }
    }
}