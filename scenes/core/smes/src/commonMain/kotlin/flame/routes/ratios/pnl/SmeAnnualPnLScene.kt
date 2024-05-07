@file:JsExport

package flame.routes.ratios.pnl

import cinematic.BaseScene
import cinematic.mutableLiveOf
import epsilon.RawFile
import flame.SmeSceneOption
import flame.SmeScheme
import kase.LazyState
import kase.Pending
import kase.Success
import koncurrent.FailedLater
import kotlinx.JsExport
import symphony.Form
import symphony.toForm

class SmeAnnualPnLScene(private val options: SmeSceneOption<SmeScheme>, private val label: String) : BaseScene() {

    val form = mutableLiveOf<LazyState<Form<Any, SmeAnnualPnLOutput, SmeAnnualPnLFields>>>(Pending)
    private val logger by options.logger
    fun initialize() {
        val f = SmeAnnualPnLFields(SmeAnnualPnLOutput()).toForm(
            heading = "Income Statement",
            details = "Income Statement for $label",
            logger = options.logger
        ) {
            onSubmit {
                FailedLater("Not yet implemented")
            }
        }
        form.value = Success(f)
    }

    fun upload(file: RawFile) {
        logger.error("Uploading files has not yet been implemented")
    }

    fun deInitialize() {
        form.value = Pending
    }
}