@file:JsExport

package flame.route.ratios.utils

import kollections.List
import kollections.emptyList
import kollections.isEmpty
import kollections.map
import kotlinx.JsExport
import kotlinx.JsExportIgnore

data class SmeEntryField(
    val name: String,
    val isAggregate: Boolean = false,
    val children: List<SmeEntryField> = emptyList()
) {

    @JsExportIgnore
    constructor(name: String, children: List<String>) : this(name, false, children.map { SmeEntryField(name = it) })

    val isInput by lazy { !isAggregate && children.isEmpty() }
}