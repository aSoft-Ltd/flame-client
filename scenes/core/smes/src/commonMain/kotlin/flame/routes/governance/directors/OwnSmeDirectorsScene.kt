@file:JsExport

package flame.routes.governance.directors

import flame.OwnSmeApi
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.transformers.toPresenter
import kollections.emptyList
import koncurrent.later.then
import koncurrent.later.zip
import kotlinx.JsExport

class OwnSmeDirectorsScene(private val options: SmeSceneOptions<OwnSmeApi>) : SmeDirectorsScene(options) {
    private val api get() = options.api
    fun initialize() = paginator.initialize { p,source->
        api.load().zip(options.auth.session()) { (sme, session) ->
            sme.toPresenter(options.toAttachmentOptions(session))
        }.then {
            presenter = it
            it.toDirectors()
        }
    }
}