@file:JsExport

package flame.routes.ratios.pnl

import cinematic.BaseScene
import cinematic.mutableLiveOf
import flame.SmeApi
import flame.SmeSceneOption
import flame.routes.ratios.pnl.utils.defaultTemplate
import flame.routes.ratios.utils.SmeEntryField
import kase.LazyState
import kase.Pending
import kase.Success
import kollections.List
import kollections.iEmptyList
import kollections.iListOf
import kollections.toIList
import kotlin.math.floor
import kotlinx.JsExport
import krono.currentInstant

class SmePnLScene(private val options: SmeSceneOption<SmeApi>) : BaseScene() {

    val template = mutableLiveOf<List<SmeEntryField>>(iEmptyList())
    val statements = mutableLiveOf<LazyState<List<SmeAnnualPnLScene>>>(Pending)

    private companion object {
        val MAX_HISTORY = 3
        val MAX_FUTURES = 5
    }

    fun initialize() {
        val current = options.clock.currentInstant().atSystemZone().year
        val century = 1000 * floor(current.toDouble() / 1000).toInt()
        val history = buildList {
            repeat(MAX_HISTORY) {
                add(current - (MAX_HISTORY - it))
            }
        }

        val futures = buildList {
            repeat(MAX_FUTURES) {
                add(current + it + 1)
            }
        }

        val scenes = (history + current + futures).map { year ->
            val label = "${year}/${(year + 1) - century}"
            SmeAnnualPnLScene(options, label).apply { initialize() }
        }.toIList()

        template.value = defaultTemplate()
        statements.value = Success(scenes)
    }

    fun deInitialize() {
        statements.value = Pending
    }
}