@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.governance

import flame.forms.selectYesOrNo
import kotlinx.JsExport
import symphony.Fields
import symphony.integer

class SmeGovernanceFields(output: SmeGovernanceOutput) : Fields<SmeGovernanceOutput>(output) {

    val insuranceScheme = selectYesOrNo(
        name = output::insuranceScheme,
        label = "Does the company have any insurance scheme relating to employees? If Yes state which employees and provide insurance contract"
    )

    val noOfJobs = integer(
        name = output::noOfJobs,
        label = "How many job will be created from this investment?"
    )

    val skillShortfall = selectYesOrNo(
        name = output::skillShortfall,
        label = "Are there any identified skills/managerial shortfall currently in the business?"
    )

    val labour = selectYesOrNo(
        name = output::labour,
        label = "Is the required labour (if any) freely available?"
    )

    val unionised = selectYesOrNo(
        name = output::unionised,
        label = "Are the staff unionised? If Yes please state which union."
    )

    val successPlan = selectYesOrNo(
        name = output::successPlan,
        label = "Is a succession plan in place?"
    )

    val organogram = selectYesOrNo(
        name = output::organogram,
        label = "Is there an organogram in place?"
    )

    val disputes = selectYesOrNo(
        name = output::disputes,
        label = "Have there been any labour disputes, arbitration, or grievances settled or outstanding over the past three years"
    )

    val specialist = selectYesOrNo(
        name = output::specialist,
        label = "Are there any key and/or specialist skills required by the business?"
    )
}