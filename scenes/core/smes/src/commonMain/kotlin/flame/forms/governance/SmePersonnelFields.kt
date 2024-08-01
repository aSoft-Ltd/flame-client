@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.forms.governance

import epsilon.file
import kollections.listOf
import kotlinx.JsExport
import symphony.Fields
import symphony.Option
import symphony.double
import symphony.selectSingle
import symphony.text

class SmePersonnelFields(output: SmeGoverningPersonnelOutput) : Fields<SmeGoverningPersonnelOutput>(output) {

    val name = text(output::name)

    val role = text(output::role)

    val qualification = selectSingle(
        name = output::qualification,
        items = listOf("Certificate", "Diploma", "Advanced Diploma", "Bachelors Degree", "Honors Degree", "Masters Degree", "PhD"),
        mapper = { Option(it, it) }
    )

    val fieldOfStudy = text(output::fieldOfStudy)

    val experience = double(output::experience)

    val resume = file(output::resume)
}