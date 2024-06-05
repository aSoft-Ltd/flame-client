@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "NOTHING_TO_INLINE")

package flame.routes.governance.personnel

import flame.SmeDto
import flame.SmePresenter
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.forms.governance.SmePersonnelFields
import flame.governance.SmeGovernanceDto
import flame.governance.SmeGoverningPersonnelDto
import flame.transformers.governance.toOutput
import flame.transformers.governance.toParams
import flame.transformers.toPresenter
import kollections.List
import kollections.emptyList
import kollections.map
import kollections.minus
import kollections.plus
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.later.zip
import koncurrent.toLater
import kotlinx.JsExport
import symphony.Confirm
import symphony.LinearCollectionScene
import symphony.Peekaboo
import symphony.linearPaginatorOf
import symphony.selectorOf
import symphony.toForm

abstract class SmePersonnelScene(private val options: SmeSceneOptions<SmeScheme>) : LinearCollectionScene<SmeGoverningPersonnelDto>(options) {

    internal var presenter: SmePresenter? = null

    override val paginator by lazy { linearPaginatorOf<SmeGoverningPersonnelDto>() }
    override val selector by lazy { selectorOf(paginator) }

    protected abstract val title: String

    private val existing get() = paginator.current.value.data?.items?.map { it.item } ?: emptyList()

    val form = Peekaboo { personnel: SmeGoverningPersonnelDto? ->
        val label = if (personnel == null) "Add $title" else "Edit ${personnel.name}"
        SmePersonnelFields(personnel.toOutput()).toForm(
            heading = "$title Form",
            details = label,
            logger = options.logger
        ) {
            onSubmit(label) { output ->
                output.toLater().then {
                    it.toParams()
                }.andThen {
                    val members = if (personnel != null) {
                        existing - personnel
                    } else {
                        existing
                    }
                    options.api.update(updated(members + it))
                }.zip(options.auth.session()) { (dto, session) ->
                    dto.toPresenter(options.toAttachmentOptions(session))
                }
            }
            onSuccess {
                paginator.refreshCurrentPage()
                options.bus.dispatch(options.topic.progressMade())
                hide()
            }
        }
    }

    val confirm = Confirm { personnel: SmeGoverningPersonnelDto ->
        heading = "Delete ${personnel.name}"
        details = "Are you sure you want to delete ${personnel.name}"
        message = "Deleting ${personnel.name}, please wait . . ."
        onConfirm("Delete") {
            options.api.update(updated(existing - personnel)).finally {
                paginator.refreshCurrentPage()
                options.bus.dispatch(options.topic.progressMade())
            }
        }
    }

    private fun updated(personnel: List<SmeGoverningPersonnelDto>): SmeDto {
        val sme = presenter ?: throw MissingPresenterException()
        val governance = sme.src.governance ?: SmeGovernanceDto()
        return sme.src.copy(governance = governance.updated(personnel))
    }

    protected abstract fun SmeGovernanceDto.updated(personnel: List<SmeGoverningPersonnelDto>) : SmeGovernanceDto


    fun showAddForm() = form.show(null)

    fun edit(personnel: SmeGoverningPersonnelDto) = form.show(personnel)

    fun delete(personnel: SmeGoverningPersonnelDto) = confirm.show(personnel)

    fun deInitialize() {
        form.hide()
        confirm.hide()
        paginator.deInitialize()
    }


    internal companion object {
        fun MissingPresenterException() = IllegalStateException("This scene is unaware of the sme presenter, please initialize it properly")
    }
}