@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import kase.catching
import kollections.List
import kotlin.js.JsExport

data class TextMessageStatuses(
    val history: List<TextMessageStatus>
) {
    val current get() = catching { history.last() }
}