@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import flame.EntitiesApi
import flame.EntityScenesConfig
import identifier.LegalEntityPresenter
import identifier.transformers.toPresenters
import identifier.utils.columnsOfLegalEntity
import kase.bagOf
import koncurrent.Later
import kronecker.LoadOptions
import symphony.CollectionScene
import symphony.actionsOf
import kotlinx.JsExport
import symphony.PaginationManager
import symphony.linearPaginatorOf

abstract class EntityPickingMode(val config: EntityScenesConfig<EntitiesApi>) : CollectionScene<LegalEntityPresenter>(config), EntityFieldMode {
    private val handler = bagOf<(LegalEntityPresenter) -> Unit>()

    private val api = config.api

    override val paginator by lazy { linearPaginatorOf<LegalEntityPresenter>() }

    fun initialize(callback: ((LegalEntityPresenter) -> Unit)?) {
        handler.value = callback
        paginator.initialize { no, capacity ->
            Later(LoadOptions(no, capacity, searchBox.output)).andThen {
                api.load(it)
            }.then {
                it.toPresenters()
            }
        }
    }

    override val actions = actionsOf(selector) {
        single { onAdd { handler.valueOrNull()?.invoke(it) } }
    }

    override val columns = columnsOfLegalEntity()

    fun deInitialize(clearPages: Boolean = true) {
        handler.value = null
        paginator.deInitialize(clearPages)
    }
}