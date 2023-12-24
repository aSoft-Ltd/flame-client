@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "NOTHING_TO_INLINE")

package flame.routes.admin.directors

import cinematic.BaseScene
import cinematic.mutableLiveOf
import flame.SmeApi
import flame.SmeSceneOption
import flame.admin.SmeDirectorDto
import flame.transformers.admin.toOutput
import flame.transformers.admin.toParams
import flame.transformers.toPresenter
import kase.LazyState
import kase.Loading
import kase.Pending
import kase.toLazyState
import kollections.List
import kollections.minus
import kollections.emptyList
import kollections.plus
import koncurrent.later.finally
import koncurrent.toLater
import koncurrent.later.then
import koncurrent.later.andThen
import kotlinx.JsExport
import symphony.Confirm
import symphony.Peekaboo
import symphony.toForm

class SmeDirectorsScene2(private val options: SmeSceneOption<SmeApi>) : BaseScene() {

    val directors = mutableLiveOf<LazyState<List<SmeDirectorDto>>>(Pending)

    val form = Peekaboo { sme: SmeDirectorDto? ->
        val label = if (sme == null) "Add Director" else "Edit ${sme.name}"

        SmeDirectorFields(sme.toOutput()).toForm(
            heading = "Director's form",
            details = label,
            logger = options.logger
        ) {
            onSubmit(label) { output ->
                output.toLater().then {
                    it.toParams()
                }.andThen {
                    val rectors = if (sme != null) {
                        existing - sme
                    } else {
                        existing
                    }
                    options.api.admin.updateDirectors(rectors + it)
                }.then {
                    it.toPresenter()
                }
            }
            onSuccess {
                initialize()
                options.bus.dispatch(options.topic.progressMade())
                hide()
            }
        }
    }

    val confirm = Confirm { director: SmeDirectorDto ->
        heading = "Delete ${director.name}"
        details = "Are you sure you want to delete ${director.name}"
        message = "Deleting ${director.name}, please wait . . ."
        onConfirm("Delete") {
            options.api.admin.updateDirectors(existing - director).finally {
                initialize()
                options.bus.dispatch(options.topic.progressMade())
            }
        }
    }

    fun initialize() {
        directors.value = Loading("Loading directors, please wait . . .")
        options.api.load().then {
            it.admin?.directors ?: emptyList()
        }.finally {
            directors.value = it.toLazyState()
        }
    }

    fun showAddForm() = form.show(null)

    fun edit(director: SmeDirectorDto) = form.show(director)

    private val existing get() = directors.value.data ?: throw IllegalStateException("editing null shareholder's is prohibited")
    fun delete(director: SmeDirectorDto) = confirm.show(director)

    fun deInitialize() {
        directors.value = Pending
        form.hide()
        confirm.hide()
    }
}