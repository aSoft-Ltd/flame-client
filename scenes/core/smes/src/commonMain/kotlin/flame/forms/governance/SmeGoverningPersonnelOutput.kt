@file:JsExport

package flame.forms.governance

import epsilon.FileOutput
import flame.SmeDto
import flame.governance.SmeGoverningPersonnelDto
import kotlinx.JsExport

data class SmeGoverningPersonnelOutput(
    val src: SmeGoverningPersonnelDto?,
    var name: String?,
    var role: String?,
    var qualification: String?,
    var fieldOfStudy: String?,
    var experience: Double?,
    var resume: FileOutput?
)