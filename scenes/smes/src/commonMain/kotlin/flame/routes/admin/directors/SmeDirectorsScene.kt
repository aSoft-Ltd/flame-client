@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin.directors

import cinematic.BaseScene
import cinematic.mutableLiveOf
import flame.SmeApi
import flame.admin.SmeDirectorDto
import flame.SmeDto
import flame.SmeSceneOption
import flame.transformers.admin.toOutput
import flame.transformers.admin.toParams
import koncurrent.toLater
import kotlin.js.JsExport
import symphony.ConfirmationBox
import symphony.Form
import symphony.lazyListOf
import symphony.paginatorOf
import symphony.toForm

class SmeDirectorsScene(private val options: SmeSceneOption<SmeApi>) : BaseScene() {

    private val directors = mutableListOf<SmeDirectorDto>()

    val form = mutableLiveOf<Form<SmeDto, SmeDirectorOutput, SmeDirectorFields>?>(null)

    val confirm = mutableLiveOf<ConfirmationBox?>(null)

    private val paginator = paginatorOf(directors, 10)

    val list = lazyListOf(paginator)
    fun initialize() = options.api.load().then {
        it.showDirectors()
    }

    private fun SmeDto.showDirectors() {
        directors.clear()
        admin?.directors?.forEach { directors.add(it) }
        paginator.refreshAllPages()
    }

    fun showAddForm() {
        form.value = form(null)
    }

    fun edit(director: SmeDirectorDto) {
        form.value = form(director)
    }

    fun delete(director: SmeDirectorDto) {
        confirm.value = ConfirmationBox(
            heading = "Delete ${director.name}",
            details = "Are you sure you want to delete ${director.name}"
        ) {
            onCancel { confirm.value = null }
            onConfirm {
                options.api.admin.updateDirectors(directors - director).then {
                    it.showDirectors()
                    confirm.value = null
                }
            }
        }
    }

    fun deInitialize() {
        paginator.deInitialize(true)
    }

    private fun form(dto: SmeDirectorDto?) = SmeDirectorFields(dto.toOutput()).toForm(
        heading = "Director's form",
        details = if (dto == null) "Add Director" else "Edit ${dto.name}",
        logger = options.logger
    ) {
        onCancel { form.value = null }
        onSubmit { output ->
            output.toLater().then {
                it.toParams()
            }.andThen {
                val rectors = if (dto != null) {
                    directors - dto
                } else {
                    directors
                }
                options.api.admin.updateDirectors(rectors + it)
            }
        }
        onSuccess { sme: SmeDto ->
            sme.showDirectors()
        }
    }
}