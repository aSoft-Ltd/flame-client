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
import kollections.emptyList
import kollections.minus
import kollections.plus
import koncurrent.later.finally
import koncurrent.toLater
import koncurrent.later.then
import koncurrent.later.andThen
import kotlinx.JsExport
import symphony.ConfirmationBox
import symphony.Peekaboo
import symphony.toForm

class SmeDirectorsScene(private val options: SmeSceneOption<SmeApi>) : BaseScene() {

    val directors = mutableLiveOf<LazyState<List<SmeDirectorDto>>>(Pending)

    val form = mutableLiveOf<SmeDirectorForm?>(null)

    val confirm = mutableLiveOf<ConfirmationBox?>(null)

    fun initialize() {
        directors.value = Loading("Loading directors, please wait . . .")
        options.api.load().then {
            it.admin?.directors ?: emptyList()
        }.finally {
            directors.value = it.toLazyState()
        }
    }

    fun showAddForm() {
        form.value = form(null)
    }

    fun edit(director: SmeDirectorDto) {
        form.value = form(director)
    }

    private val existing get() = directors.value.data ?: throw IllegalStateException("editing null shareholder's is prohibited")
    fun delete(director: SmeDirectorDto) {
        confirm.value = ConfirmationBox(
            heading = "Delete ${director.name}",
            details = "Are you sure you want to delete ${director.name}"
        ) {
            onCancel { confirm.value = null }
            onConfirm {
                options.api.admin.updateDirectors(existing - director).then {
                    confirm.value = null
                }.finally {
                    initialize()
                    options.bus.dispatch(options.topic.progressMade())
                }
            }
        }
    }

    fun deInitialize() {
        directors.value = Pending
        form.value = null
        confirm.value = null
    }

    private inline fun SmeDirectorDto?.toLabel() = if (this == null) "Add Director" else "Edit $name"

    private fun form(dto: SmeDirectorDto?) = SmeDirectorFields(dto.toOutput()).toForm(
        heading = "Director's form",
        details = dto.toLabel(),
        logger = options.logger
    ) {
        onCancel { form.value = null }
        onSubmit(dto.toLabel()) { output ->
            output.toLater().then {
                it.toParams()
            }.andThen {
                val rectors = if (dto != null) {
                    existing - dto
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
            form.value = null
        }
    }
}