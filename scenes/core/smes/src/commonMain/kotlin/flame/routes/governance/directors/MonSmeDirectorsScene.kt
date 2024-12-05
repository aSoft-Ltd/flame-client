@file:JsExport

package flame.routes.governance.directors

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.routes.governance.advisory.SmeAdvisoryScene
import flame.transformers.toPresenter
import kollections.emptyList
import koncurrent.later.then
import koncurrent.later.zip
import kotlinx.JsExport

class MonSmeDirectorsScene(private val options: SmeSceneOptions<MonSmeScheme>) : SmeDirectorsScene(options) {
    private val api get() = options.api
    fun initialize(uid: String) = paginator.initialize { p,source->
        api.load(uid).zip(options.auth.session()) { (sme, session) ->
            sme.toPresenter(options.toAttachmentOptions(session))
        }.then {
            presenter = it
            it.toDirectors()
        }
    }
}