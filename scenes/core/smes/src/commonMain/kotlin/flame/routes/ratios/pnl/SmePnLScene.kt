@file:JsExport

package flame.routes.ratios.pnl

import cinematic.BaseScene
import cinematic.mutableLiveOf
import flame.SmeApi
import flame.SmeSceneOption
import flame.routes.ratios.pnl.utils.defaultTemplate
import flame.routes.ratios.utils.SmeEntryField
import flame.routes.ratios.utils.SmeStatements
import kase.LazyState
import kase.Pending
import kase.toSuccess
import kollections.List
import kollections.forEach
import kollections.emptyList
import kotlin.math.floor
import kotlinx.JsExport

class SmePnLScene(private val options: SmeSceneOption<SmeApi>) : BaseScene() {

    val template = mutableLiveOf<List<SmeEntryField>>(emptyList())

    val statements = mutableLiveOf<LazyState<SmeStatements<SmeAnnualPnLScene>>>(Pending)

    fun initialize() {
        template.value = defaultTemplate()
        statements.value = SmeStatements.get(options.clock).map { year ->
            val millennium = 1000 * floor(year.toDouble() / 1000).toInt()
            val label = "${year}/${(year + 1) - millennium}"
            SmeAnnualPnLScene(options, label).apply { initialize() }
        }.toSuccess()
    }

    fun deInitialize() {
        statements.value.data?.all?.forEach { it.deInitialize() }
        statements.value = Pending
        template.value = emptyList()
    }
}