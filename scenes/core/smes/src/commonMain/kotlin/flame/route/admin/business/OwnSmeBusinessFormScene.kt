@file:JsExport

package flame.route.admin.business

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.forms.admin.business.SmeBusinessFields
import flame.forms.admin.business.SmeBusinessOutput
import flame.transformers.admin.toBusinessOutput
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

class OwnSmeBusinessFormScene(
    private val options: SmeSceneOption<OwnSmeScheme>
) : SmeBusinessFormScene() {
    fun initialize(): Later<Any> {
        ui.value = Loading("Loading business information")
        return options.api.load().then {
            form(it.toBusinessOutput())
        }.finally {
            ui.value = it.toLazyState()
        }
    }

    private fun form(output: SmeBusinessOutput) = SmeBusinessFields(output).toForm(
        heading = "Business Details",
        details = "Enter your business details here",
        logger = options.logger,
    ) {
        onCancel { ui.value = Pending }
        onSubmit { output ->
            output.toLater().then {
                output.toParams()
            }.andThen {
                options.api.update(it)
            }
        }
        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}