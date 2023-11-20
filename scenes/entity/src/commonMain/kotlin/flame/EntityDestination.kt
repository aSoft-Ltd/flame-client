@file:JsExport

package flame

import kollections.JsExport

class EntityDestination(val base: String) {
    val add = "add"
    val edit = "${base}edit"
    val duplicate = "${base}duplicate"
    val sendMessage = "${base}send-message"
    val generateStatement = "${base}generate-statement"
}