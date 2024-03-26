@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "NOTHING_TO_INLINE")

package flame.route.admin.directors

import cinematic.BaseScene
import cinematic.mutableLiveOf
import flame.SmePresenter
import flame.SmeSceneOption
import flame.XSmeScheme
import flame.admin.SmeDirectorDto
import flame.forms.admin.directors.SmeDirectorFields
import flame.transformers.admin.copy
import flame.transformers.admin.toOutput
import flame.transformers.admin.toParams
import flame.transformers.toPresenter
import kase.LazyState
import kase.Pending
import kollections.List
import kollections.minus
import koncurrent.FailedLater
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.Confirm
import symphony.Peekaboo
import symphony.toForm

abstract class SmeDirectorsScene(private val options: SmeSceneOption<XSmeScheme>) : BaseScene() {

    internal var presenter: SmePresenter? = null

    val directors = mutableLiveOf<LazyState<List<SmeDirectorDto>>>(Pending)

    val form = Peekaboo { director: SmeDirectorDto? ->
        val label = if (director == null) "Add Director" else "Edit ${director.name}"

        SmeDirectorFields(director.toOutput()).toForm(
            heading = "Director's form",
            details = label,
            logger = options.logger
        ) {
            onSubmit(label) { output ->
                output.toLater().then {
                    it.toParams()
                }.andThen {
                    val rectors = if (director != null) {
                        existing - director
                    } else {
                        existing
                    }
                    val sme = presenter ?: return@andThen MissingPresenterLater()
                    options.api.update(sme.src.copy(directors = rectors))
                }.then {
                    it.toPresenter()
                }
            }
            onSuccess {
                refresh()
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
            val sme = presenter ?: return@onConfirm MissingPresenterLater()
            options.api.update(sme.src.copy(directors = existing - director)).finally {
                refresh()
                options.bus.dispatch(options.topic.progressMade())
            }
        }
    }

    abstract fun refresh()

    fun showAddForm() = form.show(null)

    fun edit(director: SmeDirectorDto) = form.show(director)

    private val existing get() = directors.value.data ?: throw IllegalStateException("editing null shareholder's is prohibited")
    fun delete(director: SmeDirectorDto) = confirm.show(director)

    fun deInitialize() {
        directors.value = Pending
        form.hide()
        confirm.hide()
    }


    internal companion object {

        fun MissingPresenterException() = IllegalStateException("This screen is unaware of the sme presenter, please initialize it properly")
        fun MissingPresenterLater() = FailedLater(MissingPresenterException())
    }
}