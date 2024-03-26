@file:JsExport

package flame.route.ratios.pnl

import cinematic.BaseScene
import cinematic.mutableLiveOf
import flame.SmeApi
import flame.SmeSceneOption
import flame.XSmeScheme
import flame.route.ratios.pnl.utils.defaultTemplate
import flame.route.ratios.utils.SmeEntryField
import flame.route.ratios.utils.SmeStatements
import kase.LazyState
import kase.Pending
import kase.toSuccess
import kollections.List
import kollections.forEach
import kollections.emptyList
import kotlin.math.floor
import kotlinx.JsExport

abstract class SmePnLScene : BaseScene() {

    val template = mutableLiveOf<List<SmeEntryField>>(emptyList())

    val statements = mutableLiveOf<LazyState<SmeStatements<SmeAnnualPnLScene>>>(Pending)

    fun deInitialize() {
        statements.value.data?.all?.forEach { it.deInitialize() }
        statements.value = Pending
        template.value = emptyList()
    }
}