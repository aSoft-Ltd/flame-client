@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "OPT_IN_USAGE")

package flame.routes.admin.contacts

import flame.utils.FormScene
import kase.Pending
import kase.Success
import koncurrent.toLater
import kotlin.js.JsExport
import lexi.LoggerFactory
import symphony.toForm

class ContactDetailsFormScene(
    private val logger: LoggerFactory
) : FormScene<ContactDetailsFields>() {
    fun initialize() {
        ui.value = Success(form())
    }

    private fun form() = ContactDetailsFields(ContactDetailsOutput()).toForm(
        heading = "Contact Details",
        details = "Enter your contact details here",
        logger = logger,
    ) {
        onCancel { ui.value = Pending }
        onSubmit { it.toLater() }
    }
}