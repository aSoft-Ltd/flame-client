@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.route.admin.shareholders

import cinematic.BaseScene
import cinematic.mutableLiveOf
import flame.SmePresenter
import flame.SmeSceneOption
import flame.XSmeScheme
import flame.admin.SmeShareholderDto
import flame.forms.admin.shareholders.SmeShareholderFields
import flame.transformers.admin.copy
import flame.transformers.admin.toOutput
import flame.transformers.admin.toParams
import flame.transformers.toPresenter
import kase.LazyState
import kase.Pending
import kollections.List
import kollections.minus
import kollections.plus
import koncurrent.FailedLater
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.Confirm
import symphony.Peekaboo
import symphony.toForm

abstract class SmeShareholderScene(private val options: SmeSceneOption<XSmeScheme>) : BaseScene() {

    val shareholders = mutableLiveOf<LazyState<List<SmeShareholderDto>>>(Pending)

    internal var presenter: SmePresenter? = null

    val form = Peekaboo { dto: SmeShareholderDto? ->
        val label = if (dto == null) "Add Shareholder" else "Edit ${dto.name}"
        SmeShareholderFields(dto.toOutput()).toForm(
            heading = "Shareholder's form",
            details = label,
            logger = options.logger
        ) {
            onSubmit(label) { output ->
                output.toLater().then {
                    it.toParams()
                }.andThen {
                    val holders = if (dto != null) {
                        existing - dto
                    } else {
                        existing
                    }
                    val sme = presenter ?: return@andThen MissingPresenterLater()
                    options.api.update(sme.src.copy(holders + it))
                }.then {
                    it.toPresenter()
                }
            }
            onSuccess {
                refresh()
                hide()
                options.bus.dispatch(options.topic.progressMade())
            }
        }
    }

    val confirm = Confirm { holder: SmeShareholderDto ->
        heading = "Delete ${holder.name}"
        details = "Are you sure you want to delete ${holder.name}"

        onConfirm {
            val sme = presenter ?: return@onConfirm MissingPresenterLater()
            options.api.update(sme.src.copy(existing - holder)).finally {
                refresh()
                options.bus.dispatch(options.topic.progressMade())
            }
        }
    }

    abstract fun refresh()

    fun showAddForm() = form.show(null)

    fun edit(shareholder: SmeShareholderDto) = form.show(shareholder)

    private val existing get() = shareholders.value.data ?: throw IllegalStateException("editing null shareholder's is prohibited")

    fun deInitialize() {
        shareholders.value = Pending
        form.hide()
        confirm.hide()
    }

    internal companion object {

        fun MissingPresenterException() = IllegalStateException("This scene is unaware of the sme presenter, please initialize it properly")
        fun MissingPresenterLater() = FailedLater(MissingPresenterException())
    }
}