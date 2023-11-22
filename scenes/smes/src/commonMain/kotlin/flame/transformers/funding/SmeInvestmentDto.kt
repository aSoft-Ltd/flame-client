@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.funding

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