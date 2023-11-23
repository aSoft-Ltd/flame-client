@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.funding

import flame.SmeSectionProgress
import flame.funding.SmeBreakdownDto
import flame.funding.SmeInvestmentDto
import flame.routes.funding.breakdown.SmeBreakdownOutput
import flame.routes.funding.investments.SmeInvestmentOutput

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

fun SmeBreakdownDto?.toProgress(): SmeSectionProgress {
    val total = listOf(
        this?.acquisition,
        this?.capex,
        this?.capital,
        this?.finance
    )
    val completed = total.filterNotNull()
    return SmeSectionProgress(completed.size, total.size)
}