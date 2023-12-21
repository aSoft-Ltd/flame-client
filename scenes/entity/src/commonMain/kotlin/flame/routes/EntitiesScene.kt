@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes

import captain.NavigateFunction
import flame.EntityDestination
import flame.EntityScenesConfig
import flame.utils.confirmDelete
import flame.utils.customerActions
import flame.utils.dispatchInvalidateCache
import flame.utils.subscribeInvalidateCache
import flame.utils.subscriberBag
import identifier.LegalEntityApi
import identifier.LegalEntityPresenter
import identifier.transformers.toPresenter
import identifier.transformers.toPresenters
import identifier.utils.columnsOfLegalEntity
import kollections.List
import kollections.map
import koncurrent.Later
import kronecker.LoadOptions
import symphony.CollectionScene
import symphony.Confirmable
import symphony.actionsOf
import symphony.linearPaginatorOf
import kotlinx.JsExport
import kotlin.js.JsName

abstract class EntitiesScene(
    config: EntityScenesConfig<LegalEntityApi>
) : CollectionScene<LegalEntityPresenter>(config), Confirmable by Confirmable() {

    private val bus = config.localBus
    private val busSubscriber = bus.subscriberBag
    private var navigateTo = NavigateFunction()
    private val api = config.api

    override val paginator by lazy { linearPaginatorOf<LegalEntityPresenter>() }
    fun initialize(navigate: NavigateFunction) {
        switchToLatestSelectedView()
        paginator.initialize { no, capacity ->
            Later(LoadOptions(no, capacity, searchBox.output)).andThen {
                api.load(it)
            }.then { it.toPresenters() }
        }
        navigateTo = navigate
        busSubscriber.value = bus.subscribeInvalidateCache { paginator.refreshAllPages() }
    }

    fun deInitialize() {
        paginator.deInitialize(true)
        busSubscriber.value?.unsubscribe()
        busSubscriber.clean()
    }

    fun delete(entity: LegalEntityPresenter) = api.delete(entity.uid).then {
        it?.toPresenter()
    }.then {
        hideConfirmationBox()
        bus.dispatchInvalidateCache()
    }

    @JsName("deleteMany")
    fun delete(entities: List<LegalEntityPresenter>) = api.deleteBulk(entities.map { it.uid }).then {
        hideConfirmationBox()
        bus.dispatchInvalidateCache()
    }

    override val actions = actionsOf(selector) {
        primary {
            val destination = EntityDestination("")
            onAdd("") { navigateTo(destination.add) }
        }
        single { entity ->
            customerActions(navigateTo, "${entity.uid}/")
            onDelete {
                confirm.value = confirmDelete(entity) {
                    onCancel { hideConfirmationBox() }
                    onConfirm { delete(entity) }
                }
            }
        }
        multi { customers ->
            onDeleteAll(customers) {
                confirm.value = confirmDelete(customers) {
                    onCancel { hideConfirmationBox() }
                    onConfirm { delete(customers) }
                }
            }
        }
    }

    override val columns = columnsOfLegalEntity()
}