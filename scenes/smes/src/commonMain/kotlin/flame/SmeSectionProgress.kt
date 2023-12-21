@file:JsExport

package flame

import kotlinx.JsExport

class SmeSectionProgress(
    val completed: Int,
    val total: Int
) {
    val uncompleted by lazy { total - completed }

    val percent by lazy {
        if (total == 0) 0 else (completed * 100) / total
    }

    val fraction by lazy {
        if (total == 0) 0.0 else completed.toDouble() / total
    }

    operator fun plus(other: SmeSectionProgress) = SmeSectionProgress(completed + other.completed, total + other.total)
}