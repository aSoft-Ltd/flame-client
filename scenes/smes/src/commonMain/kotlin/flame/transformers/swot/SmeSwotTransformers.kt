package flame.transformers.swot

import flame.SmeSectionProgress
import flame.swot.SmeSwotDto
import flame.transformers.utils.aggregate

internal fun SmeSwotDto?.toProgress(min: Int = 4) = if (this == null) SmeSectionProgress(0, min) else listOf(
    strengths.toProgress(min),
    weaknesses.toProgress(min),
    opportunities.toProgress(min),
    threats.toProgress(min)
).aggregate()

private fun Collection<String>.toProgress(min: Int) = if (size >= min) {
    SmeSectionProgress(min, min)
} else {
    SmeSectionProgress(size, min)
}