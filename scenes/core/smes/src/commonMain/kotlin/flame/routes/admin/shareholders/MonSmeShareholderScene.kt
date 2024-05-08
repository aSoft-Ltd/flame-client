@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.admin.shareholders

import flame.MonSmeScheme
import flame.SmeSceneOptions
import flame.transformers.toPresenter
import kase.Loading
import kase.toLazyState
import kollections.emptyList
import koncurrent.Later
import koncurrent.later.andThen
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.later.zip
import koncurrent.toLater
import kotlinx.JsExport

class MonSmeShareholderScene(private val options: SmeSceneOptions<MonSmeScheme>) : SmeShareholderScene(options) {

    fun initialize(uid: String): Later<Any> {
        shareholders.value = Loading("Fetching shareholders, please wait . . .")
        return options.api.load(uid).zip(options.auth.session()) { (dto, session) ->
            presenter = dto.toPresenter(options.toAttachmentOptions(session))
            dto.admin?.shareholders ?: emptyList()
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
        }.zip(options.auth.session()) { (dto, session) ->
            presenter = dto.toPresenter(options.toAttachmentOptions(session))
            dto.admin?.shareholders ?: emptyList()
        }.finally {
            shareholders.value = it.toLazyState()
        }
    }
}