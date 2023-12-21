package flame.transformers.finance

import flame.finance.SmeFinanceDto
import flame.transformers.utils.aggregate
import kollections.listOf

internal fun SmeFinanceDto?.toProgress() = listOf(
    this?.office.toProgress(),
    this?.status.toProgress(),
    this?.acquisition.toProgress()
).aggregate()