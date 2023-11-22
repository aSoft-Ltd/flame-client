@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.admin.SmeShareholderDto
import flame.routes.admin.shareholders.SmeShareholderOutput

inline fun SmeShareholderDto?.toOutput() = SmeShareholderOutput(
    name = this?.name,
    currentShareholding = this?.currentShareholding,
    postInvestmentShareholding = this?.postInvestmentShareholding,
    isJuristic = this?.isJuristic,
    beneficiary = this?.beneficiary,
)

inline fun SmeShareholderOutput.toParams() = SmeShareholderDto(
    name = name,
    currentShareholding = currentShareholding,
    postInvestmentShareholding = postInvestmentShareholding,
    isJuristic = isJuristic,
    beneficiary = beneficiary,
)