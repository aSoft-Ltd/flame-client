@file:JsExport

package flame.routes.info

import cinematic.LazyScene
import cinematic.MutableLive
import cinematic.mutableLiveOf
import flame.SmeInfoPresenter
import flame.SmePresenter
import flame.SmeProgress
import kase.LazyState
import kase.Pending
import kotlinx.JsExport

abstract class SmeInfoScene : LazyScene<SmeProgress>() {
    val info:MutableLive<LazyState<SmeInfoPresenter>> = mutableLiveOf(Pending)
}