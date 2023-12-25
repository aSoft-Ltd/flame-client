@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes

import flame.EntityScenesConfig
import flame.TextMessageDelivered
import flame.TextMessageDraft
import flame.TextMessageFailed
import flame.TextMessagePresenter
import flame.TextMessageSent
import flame.TextMessageStatus
import flame.TextMessageStatuses
import krono.Instant
import krono.PresenterPattern
import krono.TimeZones
import krono.toDateTimePresenter
import symphony.CollectionScene
import kotlinx.JsExport
import kotlin.random.Random
import kollections.buildList
import kollections.add
import kollections.toList

abstract class MessagesScene(config: EntityScenesConfig<*>) : CollectionScene<TextMessagePresenter>(config) {
    override val columns = columnsOf {
        selectable()
        column("content") { it.item.content }
        column("status") {
            val status = it.item.status.current.getOrNull() ?: return@column "Unknown"
            status::class.simpleName ?: "unknown"
        }
        column("date/time") {
            val status = it.item.status.current.getOrNull() ?: return@column "Unknown"
            status.on.toDateTimeString()
        }
    }

    companion object {
        private val pattern = PresenterPattern(
            date = "{YYYY}-{MM}-{DD}",
            time = "{HH}:{mm}:{ss}",
            dateTime = "{YYYY}-{MM}-{DD} {HH}:{mm}:{ss}"
        )

        fun fakeMessageStatuses(): TextMessageStatuses {
            val stamp = 1694049541286L
            val history = buildList {
                for (i in 1..Random.nextLong(2, 5)) {
                    val on = Instant(stamp+ (i*1000)).toDateTimePresenter(TimeZones.System, pattern)
                    when (i) {
                        1L -> add(TextMessageDraft(on))
                        2L -> add(TextMessageSent(on))
                        3L -> add(TextMessageDelivered(on))
                        else -> add(TextMessageFailed(on))
                    }
                }
            }
            return TextMessageStatuses(history)
        }

        private val messages = listOf(
            "Glad to have you back",
            "It was nice doing business with you",
            "Thank you for shopping with us",
            "Your complaint has been received",
            "We will reach out to you when we resolve the issue",
            "Your feedback is greatly appreciated",
            "We checked and found that the receipt issues has been resolved. Please confirm if it has reflected on your end",
            "Something is not quite right, the CEO will address this personally"
        )

        fun fakeMessages() = messages.shuffled().mapIndexed { index, s ->
            TextMessagePresenter(
                uid = "fake-message-${index+1}",
                content = s,
                status = fakeMessageStatuses()
            )
        }.toList()
    }
}