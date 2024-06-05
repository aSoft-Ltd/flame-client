@file:JsExport

package flame.routes.governance.management.committee

import flame.OwnSmeApi
import flame.SmeSceneOptions
import flame.transformers.toPresenter
import koncurrent.later.then
import koncurrent.later.zip
import kotlinx.JsExport

class OwnSmeManagementCommitteeScene(private val options: SmeSceneOptions<OwnSmeApi>) : SmeManagementCommitteeScene(options) {
    private val api get() = options.api
    fun initialize() = paginator.initialize {
        api.load().zip(options.auth.session()) { (sme, session) ->
            sme.toPresenter(options.toAttachmentOptions(session))
        }.then {
            presenter = it
            it.toMember()
        }
    }
}