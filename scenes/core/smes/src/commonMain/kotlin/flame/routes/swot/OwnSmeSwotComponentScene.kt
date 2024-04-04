@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.swot

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.forms.swot.SwotComponent
import flame.swot.SmeSwotDto
import flame.transformers.toPresenter
import kase.Loading
import kase.toLazyState
import kollections.List
import kollections.emptyList
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport

class OwnSmeSwotComponentScene(
    private val options: SmeSceneOption<OwnSmeScheme>,
    component: SwotComponent,
    private val getter: (SmeSwotDto?) -> List<String>?,
) : SmeSwotComponentScene(options, component) {

    fun initialize() {
        ui.value = Loading("Fetching information, please wait . . .")
        options.api.load().then {
            presenter = it.toPresenter()
        }.then {
            getter(presenter?.swot) ?: emptyList()
        }.finally {
            ui.value = it.toLazyState()
        }
    }

    override fun refresh(dispatch: Boolean) {
        presenter.toLater().then {
            it ?: throw IllegalStateException("Initialize the scene please")
        }.andThen {
            options.api.load()
        }.then {
            getter(it.swot) ?: emptyList()
        }.finally {
            ui.value = it.toLazyState()
        }
        form.show(null)

        if (!dispatch) return
        options.bus.dispatch(options.topic.progressMade())
    }
}