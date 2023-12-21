@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import kotlinx.JsExport

data class TextMessagePresenter(
    val uid: String,
    val content: String,
    val status: TextMessageStatuses
)