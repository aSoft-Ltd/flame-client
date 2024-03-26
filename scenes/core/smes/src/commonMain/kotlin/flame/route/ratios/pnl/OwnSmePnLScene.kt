@file:JsExport

package flame.route.ratios.pnl

import flame.SmeSceneOption
import flame.XSmeScheme
import flame.route.ratios.pnl.utils.defaultTemplate
import flame.route.ratios.utils.SmeStatements
import kase.toSuccess
import kotlinx.JsExport
import kotlin.math.floor

class OwnSmePnLScene(private val options: SmeSceneOption<XSmeScheme>) : SmePnLScene() {

    fun initialize() {
        template.value = defaultTemplate()
        statements.value = SmeStatements.get(options.clock).map { year ->
            val millennium = 1000 * floor(year.toDouble() / 1000).toInt()
            val label = "${year}/${(year + 1) - millennium}"
            SmeAnnualPnLScene(options, label).apply { initialize() }
        }.toSuccess()
    }
}