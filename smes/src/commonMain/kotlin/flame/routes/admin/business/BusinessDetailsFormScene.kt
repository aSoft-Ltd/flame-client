@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package flame.routes.admin.business

import flame.utils.FormScene
import kase.Pending
import kase.Success
import koncurrent.toLater
import kotlin.js.JsExport
import lexi.LoggerFactory
import symphony.toForm

class BusinessDetailsFormScene(
    private val logger: LoggerFactory
) : FormScene<BusinessDetailsFields>() {
    fun initialize() {
        ui.value = Success(form())
    }

    private fun form() = BusinessDetailsFields(BusinessDetailsOutput()).toForm(
        heading = "Contact Details",
        details = "Enter your contact details here",
        logger = logger,
    ) {
        onCancel { ui.value = Pending }
        onSubmit { it.toLater() }
    }
}