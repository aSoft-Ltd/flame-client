@file:JsExport

package flame.routes.ratios.pnl

import flame.SmeSceneOption
import flame.SmeScheme
import flame.routes.ratios.pnl.utils.defaultTemplate
import flame.routes.ratios.utils.SmeStatements
import kase.toSuccess
import kotlinx.JsExport
import kotlin.math.floor

class MonSmePnLScene(private val options: SmeSceneOption<SmeScheme>) : SmePnLScene() {

    fun initialize(uid: String) {
        template.value = defaultTemplate()
        statements.value = SmeStatements.get(options.clock).map { year ->
            val millennium = 1000 * floor(year.toDouble() / 1000).toInt()
            val label = "${year}/${(year + 1) - millennium}"
            SmeAnnualPnLScene(options, label).apply { initialize() }
        }.toSuccess()
    }
}