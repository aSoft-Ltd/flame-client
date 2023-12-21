@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin.shareholders

import cinematic.BaseScene
import cinematic.mutableLiveOf
import flame.SmeApi
import flame.SmeDto
import flame.SmePresenter
import flame.SmeSceneOption
import flame.admin.SmeShareholderDto
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
import kotlin.js.JsExport
import symphony.ConfirmationBox
import symphony.Form
import symphony.toForm

class SmeShareholderScene(private val options: SmeSceneOption<SmeApi>) : BaseScene() {

    val shareholders = mutableLiveOf<LazyState<List<SmeShareholderDto>>>(Pending)

    val form = mutableLiveOf<Form<SmePresenter, SmeShareholderOutput, SmeShareholderFields>?>(null)
    val confirm = mutableLiveOf<ConfirmationBox?>(null)

    fun initialize() {
        shareholders.value = Loading("Fetching shareholders, please wait . . .")
        options.api.load().then {
            it.admin?.shareholders ?: emptyList()
        }.finally {
            shareholders.value = it.toLazyState()
        }
    }

    fun showAddForm() {
        form.value = form(null)
    }

    fun edit(shareholder: SmeShareholderDto) {
        form.value = form(shareholder)
    }

    private val existing get() = shareholders.value.data ?: throw IllegalStateException("editing null shareholder's is prohibited")

    fun delete(holder: SmeShareholderDto) {
        confirm.value = ConfirmationBox(
            heading = "Delete ${holder.name}",
            details = "Are you sure you want to delete ${holder.name}"
        ) {
            onCancel { confirm.value = null }
            onConfirm {
                options.api.admin.updateShareholders(existing - holder).then {
                    confirm.value = null
                }.complete {
                    initialize()
                    options.bus.dispatch(options.topic.progressMade())
                }
            }
        }
    }

    fun deInitialize() {
        shareholders.value = Pending
    }

    private fun form(dto: SmeShareholderDto?) = SmeShareholderFields(dto.toOutput()).toForm(
        heading = "Shareholder's form",
        details = if (dto == null) "Add Shareholder" else "Edit ${dto.name}",
        logger = options.logger
    ) {
        onCancel { form.value = null }
        onSubmit { output ->
            output.toLater().then {
                it.toParams()
            }.andThen {
                val holders = if (dto != null) {
                    existing - dto
                } else {
                    existing
                }
                options.api.admin.updateShareholders(holders + it)
            }.then {
                it.toPresenter()
            }
        }
        onSuccess {
            initialize()
            form.value = null
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}