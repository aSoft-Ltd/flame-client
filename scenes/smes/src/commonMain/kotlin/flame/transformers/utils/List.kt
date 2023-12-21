package flame.transformers.utils

import flame.SmeSectionProgress
import kollections.List
import kollections.filterNotNull
import kollections.reduce
import kollections.size

internal fun List<Any?>?.toCompletedIfNotEmpty() = SmeSectionProgress(this?.size ?: 0, this?.size ?: 0)

internal fun List<Any?>.toProgress() = SmeSectionProgress(filterNotNull().size, size)

internal fun List<SmeSectionProgress>.aggregate() = reduce { acc, it -> acc + it }