package flame.transformers.utils

import flame.SmeSectionProgress
import kollections.*

internal fun List<Any?>?.toCompletedIfNotEmpty():SmeSectionProgress {
    return when (this?.isEmpty()) {
        true -> SmeSectionProgress(0, 1)
        null -> SmeSectionProgress(0, 1)
        false -> SmeSectionProgress(1, 1)
    }
}

internal fun List<Any?>.toProgress() = SmeSectionProgress(filterNotNull().size, size)

internal fun List<SmeSectionProgress>.aggregate() = reduce { acc, it -> acc + it }