@file:JsExport

package flame.routes.governance.management.team

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.transformers.toPresenter
import kollections.emptyList
import koncurrent.later.then
import koncurrent.later.zip
import kotlinx.JsExport

class MonSmeManagementTeamScene(private val options: SmeSceneOptions<MonSmeScheme>) : SmeManagementTeamScene(options) {
    private val api get() = options.api
    fun initialize(uid: String) = paginator.initialize {
        api.load(uid).zip(options.auth.session()) { (sme, session) ->
            sme.toPresenter(options.toAttachmentOptions(session))
        }.then {
            presenter = it
            it.toTeam()
        }
    }
}