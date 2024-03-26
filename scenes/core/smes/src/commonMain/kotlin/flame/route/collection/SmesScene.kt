@file:JsExport

package flame.route.collection

import flame.MonSmeScheme
import flame.SmePresenter
import flame.SmeSceneOption
import flame.transformers.toPresenter
import kollections.all
import kollections.map
import koncurrent.Later
import koncurrent.later.then
import kotlinx.JsExport
import kronecker.LoadOptions
import symphony.LinearCollectionScene
import symphony.LinearPage
import symphony.actionsOf
import symphony.linearPaginatorOf

class SmesScene(private val options: SmeSceneOption<MonSmeScheme>) : LinearCollectionScene<SmePresenter>(options) {

    private val logger by options.logger

    override val paginator by lazy { linearPaginatorOf<SmePresenter>() }

    fun initialize(): Later<LinearPage<SmePresenter>> = paginator.initialize { params ->
        options.api.list(LoadOptions(params.page, params.limit)).then { smes -> smes.map { it.toPresenter() } }
    }

    fun deInitialize() {
        paginator.deInitialize(true)
    }

    override val actions = actionsOf(selector) {
        primary {
            onAdd { logger.error("Not Implemented yet") }
        }
        single {
            if (it.src.origin != "picapital") {
                onEdit { logger.error("Not implemented yet") }
                onDelete { logger.error("Not implemented yet") }
            }
        }
        multi { smes ->
            if (smes.all { it.src.origin == "picapital" }) {
                onDeleteAll { logger.error("Not implemented yet") }
            }
        }
    }
}