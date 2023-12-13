@file:JsExport

package flame.routes.ratios.utils

import kollections.List
import kollections.iEmptyList
import kotlinx.JsExport
import kotlinx.JsExportIgnore

data class SmeEntryField(
    val name: String,
    val isAggregate: Boolean = false,
    val children: List<SmeEntryField> = iEmptyList()
) {

    @JsExportIgnore
    constructor(name: String, children: List<String>) : this(name, false, children.map { SmeEntryField(name = it) })

    val isInput by lazy { !isAggregate && children.isEmpty() }
}