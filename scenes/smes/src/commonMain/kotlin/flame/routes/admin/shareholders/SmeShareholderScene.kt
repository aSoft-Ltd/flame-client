@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin.shareholders

import cinematic.BaseScene
import cinematic.mutableLiveOf
import flame.SmeApi
import flame.SmeDto
import flame.SmeSceneOption
import flame.admin.SmeShareholderDto
import flame.transformers.admin.toOutput
import flame.transformers.admin.toParams
import koncurrent.toLater
import kotlin.js.JsExport
import symphony.ConfirmationBox
import symphony.Form
import symphony.lazyListOf
import symphony.paginatorOf
import symphony.toForm

class SmeShareholderScene(private val options: SmeSceneOption<SmeApi>) : BaseScene() {

    private val shareholders = mutableListOf<SmeShareholderDto>()

    val form = mutableLiveOf<Form<SmeDto, SmeShareholderOutput, SmeShareholderFields>?>(null)
    val confirm = mutableLiveOf<ConfirmationBox?>(null)

    private val paginator = paginatorOf(shareholders, 10)

    val list = lazyListOf(paginator)
    fun initialize() = options.api.load().then {
        it.showShareholders()
    }

    private fun SmeDto.showShareholders() {
        shareholders.clear()
        admin?.shareholders?.forEach { shareholders.add(it) }
        paginator.refreshAllPages()
    }

    fun showAddForm() {
        form.value = form(null)
    }

    fun edit(shareholder: SmeShareholderDto) {
        form.value = form(shareholder)
    }

    fun delete(holder: SmeShareholderDto) {
        confirm.value = ConfirmationBox(
            heading = "Delete ${holder.name}",
            details = "Are you sure you want to delete ${holder.name}"
        ) {
            onCancel { confirm.value = null }
            onConfirm {
                options.api.admin.updateShareholders(shareholders - holder).then {
                    it.showShareholders()
                    confirm.value = null
                }.then {
                    options.bus.dispatch(options.topic.progressMade())
                }
            }
        }
    }

    fun deInitialize() {
        paginator.deInitialize(true)
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
                    shareholders - dto
                } else {
                    shareholders
                }
                options.api.admin.updateShareholders(holders + it)
            }
        }
        onSuccess { sme: SmeDto ->
            sme.showShareholders()
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}