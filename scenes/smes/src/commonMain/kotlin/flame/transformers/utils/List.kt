package flame.transformers.utils

import flame.SmeSectionProgress

internal fun List<Any?>?.toProgress() = SmeSectionProgress(this?.size ?: 0, this?.size ?: 0)

internal fun List<SmeSectionProgress>.aggregate() = reduce { acc, it -> acc + it }