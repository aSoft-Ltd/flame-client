@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import flame.SupplierScenesConfig
import flame.TextMessagePresenter
import flame.routes.MessagesScene
import symphony.paged
import kotlinx.JsExport
import symphony.PaginationManager
import symphony.linearPaginatorOf

class SupplierMessagesScene(private val config: SupplierScenesConfig<*>) : MessagesScene(config) {

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