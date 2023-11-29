@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.routes.admin.legal

import flame.SmeApi
import flame.SmeSceneOption
import flame.transformers.admin.toOutput
import flame.transformers.admin.toParams
import flame.utils.FormScene
import kase.Loading
import kase.Pending
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.toLater
import kotlin.js.JsExport
import symphony.toForm

class SmeLegalFormScene(
    private val options: SmeSceneOption<SmeApi>
) : FormScene<SmeLegalFields>() {
    fun initialize() {
        ui.value = Loading("Loading business information")
        options.api.load().then {
            it.admin?.legal.toOutput()
        }.then {
            form(it)
        }.finally {
            ui.value = it.toLazyState()
        }
    }

    private fun form(output: SmeLegalOutput) = SmeLegalFields(output).toForm(
        heading = "Legal Compliance Form",
        details = "Enter your legal information here",
        logger = options.logger,
    ) {
        onCancel { ui.value = Pending }
        onSubmit {output->
            output.toLater().then {
                it.toParams()
            }.andThen {
                options.api.admin.update(it)
            }
        }
        onSuccess {
            options.bus.dispatch(options.topic.progressMade())
        }
    }
}