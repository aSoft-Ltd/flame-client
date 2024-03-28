@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "NOTHING_TO_INLINE")

package flame.routes.admin.directors

import flame.MonSmeScheme
import flame.SmeSceneOption
import flame.transformers.toPresenter
import kase.Loading
import kase.toLazyState
import kollections.emptyList
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport

class MonSmeDirectorsScene(private val options: SmeSceneOption<MonSmeScheme>) : SmeDirectorsScene(options) {

    fun initialize(uid: String) {
        directors.value = Loading("Loading directors, please wait . . .")
        options.api.load(uid).then {
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
            options.api.load(it.uid)
        }.then {
            presenter = it.toPresenter()
            it.admin?.directors ?: emptyList()
        }.finally {
            directors.value = it.toLazyState()
        }
    }
}