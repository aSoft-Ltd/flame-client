@file:JsExport
package flame.forms.plan

import flame.plan.QnADto
import kollections.List
import kollections.forEach
import kotlinx.JsExport
import symphony.Fields
import symphony.text

class SmeQnAFields(output: List<QnADto>) : Fields<List<QnADto>>(output) {
    val questions = output.forEach {
        text(
            name = it::answer,
            label = it.question,
        )
    }
}