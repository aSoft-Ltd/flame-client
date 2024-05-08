@file:JsExport

package flame.routes.collection

import flame.MonSmeScheme
import flame.SmePresenter
import flame.SmeSceneOptions
import flame.transformers.toPresenter
import kollections.map
import koncurrent.Later
import koncurrent.later.andThen
import koncurrent.later.zip
import kotlinx.JsExport
import kronecker.LoadOptions
import symphony.LinearCollectionScene
import symphony.LinearPage
import symphony.linearPaginatorOf

class SmesScene(private val options: SmeSceneOptions<MonSmeScheme>) : LinearCollectionScene<SmePresenter>(options) {

    override val paginator by lazy { linearPaginatorOf<SmePresenter>() }

    fun initialize(): Later<LinearPage<SmePresenter>> {
        return columns.initialize().andThen {
            switchToLatestSelectedView()
            paginator.initialize { params ->
                options.api.list(LoadOptions(params.page, params.limit))
                    .zip(options.auth.session()) { (smes, session) ->
                        smes.map { it.toPresenter(options.toAttachmentOptions(session)) }
                    }
            }
        }
    }

    fun deInitialize() {
        paginator.deInitialize(true)
    }
}