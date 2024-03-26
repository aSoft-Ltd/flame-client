@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.route.admin.shareholders

import flame.OwnSmeScheme
import flame.SmeSceneOption
import flame.route.admin.directors.SmeDirectorsScene
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

class OwnSmeShareholderScene(private val options: SmeSceneOption<OwnSmeScheme>) : SmeShareholderScene(options) {

    fun initialize(): Later<Any> {
        shareholders.value = Loading("Fetching shareholders, please wait . . .")
        return options.api.load().then {
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
            options.api.load()
        }.then {
            presenter = it.toPresenter()
            it.admin?.shareholders ?: emptyList()
        }.finally {
            shareholders.value = it.toLazyState()
        }
    }
}