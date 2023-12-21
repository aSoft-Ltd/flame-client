@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.funding

import flame.funding.SmeBreakdownDto
import flame.routes.funding.breakdown.SmeBreakdownOutput
import flame.transformers.utils.toProgress
import kollections.listOf

inline fun SmeBreakdownDto?.toOutput() = SmeBreakdownOutput(
    acquisition = this?.acquisition,
    capex = this?.capex,
    capital = this?.capital,
    finance = this?.finance,
)

inline fun SmeBreakdownOutput.toParams() = SmeBreakdownDto(
    acquisition = acquisition,
    capex = capex,
    capital = capital,
    finance = finance,
)

fun SmeBreakdownDto?.toProgress() = listOf(
    this?.acquisition,
    this?.capex,
    this?.capital,
    this?.finance
).toProgress()