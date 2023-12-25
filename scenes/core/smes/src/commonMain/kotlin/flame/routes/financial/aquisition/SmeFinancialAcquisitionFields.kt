@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.financial.aquisition

import flame.utils.selectYesOrNo
import kotlinx.JsExport
import symphony.Fields

class SmeFinancialAcquisitionFields(output: SmeFinancialAcquisitionOutput) : Fields<SmeFinancialAcquisitionOutput>(output) {

    val historicalFinancialStatements = selectYesOrNo(
        name = output::statements,
        label = """
            Does the target company have past three years’ historical financial statements 
            and latest management accounts. (If yes, please attach any relevant document)
        """.trimIndent().replace("\n", " ")
    )

    val dd = selectYesOrNo(
        name = output::dd,
        label = "Have the details of your due diligence on the target company been provided?"
    )

    val mou = selectYesOrNo(
        name = output::mou,
        label = """
            Has the MOU or the sale & purchase agreement between Buyer & Seller confirming 
            proposed transaction parameters and exclusivity been signed?
            (If yes, please attach any relevant document)
        """.trimIndent().replace("\n", " ")
    )
}