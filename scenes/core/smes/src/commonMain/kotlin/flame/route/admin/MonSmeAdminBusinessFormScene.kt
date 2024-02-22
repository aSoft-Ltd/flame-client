@file:JsExport

package flame.route.admin

import flame.SmeDto
import flame.SmeMonitorApi
import flame.SmeSceneOption
import flame.forms.admin.business.SmeBusinessFields
import flame.forms.admin.business.SmeBusinessOutput
import flame.transformers.admin.copy
import flame.transformers.admin.toOutput
import flame.transformers.admin.toParams
import kase.Loading
import kase.Pending
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import symphony.toForm

class MonSmeAdminBusinessFormScene(
    private val options: SmeSceneOption<SmeMonitorApi>
) : SmeAdminBusinessScene() {
    fun initialize(uid: String): Later<Any> {
        ui.value = Loading("Loading information for business with uid = $uid")
        return options.api.load(uid).then {
            it to it.admin?.business.toOutput()
        }.then { (sme, output) ->
            form(sme, output)
        }.finally {
            ui.value = it.toLazyState()
        }
    }

    private fun form(sme: SmeDto, output: SmeBusinessOutput) = SmeBusinessFields(output).toForm(
        heading = "Business Details",
        details = "Enter ${sme.admin?.business?.name ?: "SME"} business details here",
        logger = options.logger,
    ) {
        onCancel { ui.value = Pending }
        onSubmit { output ->
            output.toLater().then {
                output.toParams()
            }.andThen {
                options.api.update(sme.copy(it))
            }
        }
        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}