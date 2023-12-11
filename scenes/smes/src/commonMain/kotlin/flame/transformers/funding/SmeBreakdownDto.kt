@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.funding

import flame.SmeSectionProgress
import flame.funding.SmeBreakdownDto
import flame.funding.SmeInvestmentDto
import flame.routes.funding.breakdown.SmeBreakdownOutput
import flame.routes.funding.investments.SmeInvestmentOutput
import flame.transformers.utils.toProgress

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