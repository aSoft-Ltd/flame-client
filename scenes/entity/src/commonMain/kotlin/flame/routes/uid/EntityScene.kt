@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import captain.NavigateFunction
import cinematic.LazyScene
import flame.EntityScenesConfig
import flame.utils.confirmDelete
import flame.utils.customerActions
import flame.utils.dispatchInvalidateCache
import flame.utils.loadingCustomer
import flame.utils.subscribeInvalidateCache
import flame.utils.subscriberBag
import identifier.LegalEntityApi
import identifier.LegalEntityPresenter
import identifier.transformers.toPresenter
import identifier.utils.loadCacheableLegalEntity
import kase.Pending
import kase.toLazyState
import koncurrent.Later
import koncurrent.later.finally
import symphony.Confirmable
import symphony.actionsOf
import symphony.removeSelectedItem
import kotlinx.JsExport

abstract class EntityScene(
    val config: EntityScenesConfig<LegalEntityApi>
) : LazyScene<LegalEntityPresenter>(Pending), Confirmable by Confirmable() {

    private val bus = config.localBus
    private val busSubscriber = bus.subscriberBag
    private var navigateTo = NavigateFunction()

    private val api = config.api

    fun initialize(navigate: NavigateFunction, uid: String): Later<LegalEntityPresenter> {
        busSubscriber.value = bus.subscribeInvalidateCache {
            config.cache.removeSelectedItem()
            loadCustomer(uid)
        }
        navigateTo = navigate
        return loadCustomer(uid)
    }

    fun loadCustomer(uid: String) = config.loadCacheableLegalEntity(uid) {
        ui.value = loadingCustomer(uid, "info")
    }.then {
        it.toPresenter()
    }.finally {
        ui.value = it.toLazyState()
    }

    override fun deInitialize() {
        busSubscriber.value?.unsubscribe()
        busSubscriber.clean()
        ui.value = Pending
    }

    fun delete(customer: LegalEntityPresenter) = api.delete(customer.uid).then {
        hideConfirmationBox()
        bus.dispatchInvalidateCache()
    }

    val actions = actionsOf {
        customerActions(navigateTo, "./")
        onDelete {
            val entity = ui.value.data ?: return@onDelete
            confirm.value = confirmDelete(entity) {
                onCancel { hideConfirmationBox() }
                onConfirm { delete(entity) }
            }
        }
    }
}