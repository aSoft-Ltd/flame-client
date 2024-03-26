@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "NOTHING_TO_INLINE")

package flame.route.admin.directors

import flame.OwnSmeScheme
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

class OwnSmeDirectorsScene(private val options: SmeSceneOption<OwnSmeScheme>) : SmeDirectorsScene(options) {

    fun initialize(): Later<Any> {
        directors.value = Loading("Loading directors, please wait . . .")
        return options.api.load().then {
            presenter = it.toPresenter()
            it.admin?.directors ?: emptyList()
        }.finally {
            directors.value = it.toLazyState()
        }
    }

    override fun refresh() {
        directors.value = Loading("Updating directors list, please wait . . .")
        presenter.toLater().then {
            it ?: throw MissingPresenterException()
        }.andThen {
            options.api.load()
        }.then {
            presenter = it.toPresenter()
            it.admin?.directors ?: emptyList()
        }.finally {
            directors.value = it.toLazyState()
        }
    }
}