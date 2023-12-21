package flame.transformers.funding

import flame.funding.SmeFundingDto
import flame.transformers.utils.aggregate
import kollections.listOf

fun SmeFundingDto?.toProgress() = listOf(
    this?.investment.toProgress(),
    this?.breakdown.toProgress(),
    this?.acquisition.toProgress()
).aggregate()