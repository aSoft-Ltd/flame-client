@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.funding

import flame.SmeSectionProgress
import flame.funding.SmeAcquisitionDto
import flame.funding.SmeBreakdownDto
import flame.funding.SmeInvestmentDto
import flame.routes.funding.acquisition.SmeAcquisitionOutput
import flame.routes.funding.breakdown.SmeBreakdownOutput
import flame.routes.funding.investments.SmeInvestmentOutput
import flame.transformers.utils.toProgress

inline fun SmeAcquisitionDto?.toOutput() = SmeAcquisitionOutput(
    price = this?.price,
    dd = this?.dd,
    valuation = this?.valuation,
)

inline fun SmeAcquisitionOutput.toParams() = SmeAcquisitionDto(
    price = price,
    dd = dd,
    valuation = valuation,
)

fun SmeAcquisitionDto?.toProgress() = listOf(this?.price, this?.dd, this?.valuation).toProgress()