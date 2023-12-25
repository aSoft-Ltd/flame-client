@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame

import krono.DateTimePresenter
import kotlinx.JsExport

sealed class TextMessageStatus(open val on: DateTimePresenter) {

    val asDraft get() = this as? TextMessageDraft
    val asSent get() = this as? TextMessageSent
    val asDelivered get() = this as? TextMessageDelivered
    val asFailed get() = this as? TextMessageFailed
}

data class TextMessageDraft(override val on: DateTimePresenter) : TextMessageStatus(on)
data class TextMessageSent(override val on: DateTimePresenter) : TextMessageStatus(on)
data class TextMessageDelivered(override val on: DateTimePresenter) : TextMessageStatus(on)
data class TextMessageFailed(override val on: DateTimePresenter) : TextMessageStatus(on)