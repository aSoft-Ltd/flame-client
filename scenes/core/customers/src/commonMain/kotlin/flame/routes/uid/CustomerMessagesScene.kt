@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.CustomerScenesConfig
import flame.TextMessagePresenter
import flame.routes.MessagesScene
import kotlinx.JsExport
import symphony.linearPaginatorOf
import symphony.paged

class CustomerMessagesScene(config: CustomerScenesConfig<*>) : MessagesScene(config) {

    override val paginator by lazy {
        linearPaginatorOf<TextMessagePresenter>()
    }
    fun initialize(uid: String) {
        paginator.initialize { no, capacity ->
            fakeMessages().paged(no,capacity)
        }
    }

    fun deInitialize() {
        paginator.deInitialize()
    }
}