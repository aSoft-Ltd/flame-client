@file:JsExport

package flame.routes.financial.analysis

import cinematic.LazyScene
import kase.Success
import kotlinx.JsExport
import symphony.BalanceSheetForm
import symphony.listFieldOf

class BalanceSheetScene : LazyScene<Unit>() {
    val sheets = listFieldOf<BalanceSheetForm>()

    fun initialize() {
        ui.value = Success(Unit)
    }

    fun create() : BalanceSheetForm {
        val sheet = BalanceSheetForm()
        sheets.add(sheet)
        return sheet
    }

    fun remove(sheet: BalanceSheetForm) {
        sheets.remove(sheet)
    }
}