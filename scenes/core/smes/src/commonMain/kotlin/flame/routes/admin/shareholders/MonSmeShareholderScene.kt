@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin.shareholders

import flame.MonSmeScheme
import flame.SmeSceneOption
import flame.transformers.toPresenter
import kase.Loading
import kase.toLazyState
import kollections.emptyList
import koncurrent.Later
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport

class MonSmeShareholderScene(private val options: SmeSceneOption<MonSmeScheme>) : SmeShareholderScene(options) {

    fun initialize(uid: String): Later<Any> {
        shareholders.value = Loading("Fetching shareholders, please wait . . .")
        return options.api.load(uid).then {
            presenter = it.toPresenter()
            it.admin?.shareholders ?: emptyList()
        }.finally {
            shareholders.value = it.toLazyState()
        }
    }

    override fun refresh() {
        shareholders.value = Loading("Updating shareholders list, please wait . . .")
        presenter.toLater().then {
            it ?: throw MissingPresenterException()
        }.andThen {
            options.api.load(it.uid)
        }.then {
            presenter = it.toPresenter()
            it.admin?.shareholders ?: emptyList()
        }.finally {
            shareholders.value = it.toLazyState()
        }
    }
}