@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.info

import flame.OwnSmeScheme
import flame.SmeSceneOptions
import flame.transformers.toProgress
import kase.Loading
import kase.Success
import kase.toLazyState
import koncurrent.later.finally
import koncurrent.later.then
import koncurrent.later.zip
import kotlinx.JsExport
import sanity.Subscriber

class OwnSmeInfoScene(private val options: SmeSceneOptions<OwnSmeScheme>) : SmeInfoScene() {

    private var subscriber: Subscriber? = null
    fun initialize() {
        ui.value = Loading("loading your information, please wait...")
        options.api.load().zip(options.auth.session()) { (it, session) ->
            it.toProgress(options.toAttachmentOptions(session))
        }.finally {
            ui.value = it.toLazyState()
        }
        subscriber = options.bus.subscribe(options.topic.progressMade()) { refresh() }
    }

    fun refresh() = options.api.load().zip(options.auth.session()) { (it, session) ->
        it.toProgress(options.toAttachmentOptions(session))
    }.then {
        ui.value = Success(it)
    }

    override fun deInitialize() {
        subscriber?.unsubscribe()
        subscriber = null
        super.deInitialize()
    }
}