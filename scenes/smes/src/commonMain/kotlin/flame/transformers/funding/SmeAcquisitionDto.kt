@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.funding

import flame.funding.SmeAcquisitionDto
import flame.routes.funding.acquisition.SmeAcquisitionOutput
import flame.transformers.utils.toProgress
import kollections.listOf

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