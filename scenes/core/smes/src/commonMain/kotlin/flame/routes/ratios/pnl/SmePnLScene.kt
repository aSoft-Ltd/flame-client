@file:JsExport

package flame.routes.ratios.pnl

import cinematic.BaseScene
import cinematic.mutableLiveOf
import flame.routes.ratios.utils.SmeEntryField
import flame.routes.ratios.utils.SmeStatements
import kase.LazyState
import kase.Pending
import kollections.List
import kollections.emptyList
import kollections.forEach
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