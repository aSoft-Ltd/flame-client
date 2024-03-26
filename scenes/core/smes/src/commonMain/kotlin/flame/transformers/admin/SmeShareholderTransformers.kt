@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.SmeDto
import flame.admin.SmeAdminDto
import flame.admin.SmeShareholderDto
import flame.forms.admin.shareholders.SmeShareholderOutput
import kollections.List

internal inline fun SmeDto.copy(shareholders: List<SmeShareholderDto>) = copy(
    admin = (admin ?: SmeAdminDto()).copy(shareholders = shareholders)
)

internal inline fun SmeShareholderDto?.toOutput() = SmeShareholderOutput(
    name = this?.name,
    currentShareholding = this?.currentShareholding,
    postInvestmentShareholding = this?.postInvestmentShareholding,
    isJuristic = this?.isJuristic,
    beneficiary = this?.beneficiary,
)

internal inline fun SmeShareholderOutput.toParams() = SmeShareholderDto(
    name = name,
    currentShareholding = currentShareholding,
    postInvestmentShareholding = postInvestmentShareholding,
    isJuristic = isJuristic,
    beneficiary = if (isJuristic == true) beneficiary else null,
)