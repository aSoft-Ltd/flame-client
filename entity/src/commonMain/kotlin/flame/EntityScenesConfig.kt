package flame

import bringer.Downloader
import identifier.IdentifierScenesConfig
import klip.Clipboard
import krest.Workable
import sanity.EventBus
import snitch.Snitch

interface EntityScenesConfig<out A> : IdentifierScenesConfig<A>, Workable {
    val localBus: EventBus
    val clipboard: Clipboard
    val downloader: Downloader
    val toaster: Snitch
    override fun <R> map(transformer: (A) -> R): EntityScenesConfig<R>
}