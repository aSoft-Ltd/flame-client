@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.dashboard

import cinematic.LazyScene
import flame.CustomersApi
import identifier.IdentifierScenesConfig
import kase.Failure
import kase.Loading
import kase.Pending
import kase.Success
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import kotlinx.JsExport
import kronecker.LoadSource

class CustomersDashboardScene(
    val config: IdentifierScenesConfig<CustomersApi>
) : LazyScene<DashboardData>(Pending) {

    companion object {
        val LOADING_MESSAGE = "Loading dashboard data, please wait . . ."
        val LOADING_STATE = Loading<DashboardData>(LOADING_MESSAGE)
    }

    private val api get() = config.api

    fun initialize(): Later<Unit> {
        return loadDashboard()
    }

    fun loadDashboard(): Later<Unit> {
        ui.value = LOADING_STATE
        return api.load(source = LoadSource.REMOTE).then {
            ui.value = Success(DashboardData(it))
        }.catch {
            ui.value = Failure(it)
        }
    }

    fun refresh(): Later<Unit> {
        return loadDashboard()
    }
}
