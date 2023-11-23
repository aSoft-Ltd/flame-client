@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.funding

import flame.SmeSectionProgress
import flame.funding.SmeInvestmentDto
import flame.routes.funding.investments.SmeInvestmentOutput

inline fun SmeInvestmentDto?.toOutput() = SmeInvestmentOutput(
    amount = this?.amount,
    type = this?.type,
    debtTenure = this?.debtTenure,
    equity = this?.equity,
    description = this?.description,
)

inline fun SmeInvestmentOutput.toParams() = SmeInvestmentDto(
    amount = amount,
    type = type,
    debtTenure = debtTenure,
    equity = equity,
    description = description,
)

fun SmeInvestmentDto?.toProgress(): SmeSectionProgress {
    val total = listOf(
        this?.amount,
        this?.type,
        this?.debtTenure,
        this?.equity,
        this?.description,
    )
    val completed = total.filterNotNull()
    return SmeSectionProgress(completed.size, total.size)
}