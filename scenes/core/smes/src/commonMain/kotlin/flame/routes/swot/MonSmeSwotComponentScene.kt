@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.swot

import flame.MonSmeScheme
import flame.SmeSceneOptions
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
import koncurrent.later.zip
import koncurrent.toLater
import kotlinx.JsExport

class MonSmeSwotComponentScene(
    private val options: SmeSceneOptions<MonSmeScheme>,
    component: SwotComponent,
    private val getter: (SmeSwotDto?) -> List<String>?,
) : SmeSwotComponentScene(options, component) {

    fun initialize(uid: String) {
        ui.value = Loading("Fetching information, please wait . . .")
        options.api.load(uid).zip(options.auth.session()) { (dto,session) ->
            presenter = dto.toPresenter(options.toAttachmentOptions(session))
            getter(dto.swot) ?: emptyList()
        }.finally {
            ui.value = it.toLazyState()
        }
        form.show(null)
    }

    override fun refresh(dispatch: Boolean) {
        presenter.toLater().then {
            it ?: throw IllegalStateException("Initialize the scene please")
        }.andThen {
            options.api.load(it.uid)
        }.zip(options.auth.session()) { (dto,session) ->
            presenter = dto.toPresenter(options.toAttachmentOptions(session))
            getter(dto.swot) ?: emptyList()
        }.finally {
            ui.value = it.toLazyState()
        }
        form.show(null)

        if (!dispatch) return
        options.bus.dispatch(options.topic.progressMade())
    }
}