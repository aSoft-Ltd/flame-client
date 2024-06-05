@file:JsExport

package flame.routes.governance.advisory

import flame.OwnSmeApi
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.transformers.toPresenter
import kollections.emptyList
import koncurrent.later.then
import koncurrent.later.zip
import kotlinx.JsExport

class OwnSmeAdvisoryScene(private val options: SmeSceneOptions<OwnSmeApi>) : SmeAdvisoryScene(options) {
    private val api get() = options.api
    fun initialize() = paginator.initialize {
        api.load().zip(options.auth.session()) { (sme, session) ->
            sme.toPresenter(options.toAttachmentOptions(session))
        }.then {
            presenter = it
            it.toAdvisors()
        }
    }
}