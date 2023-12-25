@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.admin

import flame.admin.SmeLegalComplianceDto
import flame.routes.admin.legal.SmeLegalOutput
import flame.transformers.utils.toProgress
import kollections.listOf

inline fun SmeLegalComplianceDto?.toOutput() = SmeLegalOutput(
    cipcAnnualReturns = this?.cipcAnnualReturns,
    registration = this?.registration,
    vatRegistration = this?.vatRegistration,
    vatNumber = this?.vatNumber,
    taxComplianceStatus = this?.taxComplianceStatus,
    incomeTaxNumber = this?.incomeTaxNumber,
    workmanCompensationOption = this?.workmanCompensationOption,
    workmanCompensationNumber = this?.workmanCompensationNumber,
)

inline fun SmeLegalOutput.toParams() = SmeLegalComplianceDto(
    cipcAnnualReturns = cipcAnnualReturns,
    registration = registration,
    vatRegistration = vatRegistration,
    vatNumber = vatNumber,
    taxComplianceStatus = taxComplianceStatus,
    incomeTaxNumber = incomeTaxNumber,
    workmanCompensationOption = workmanCompensationOption,
    workmanCompensationNumber = workmanCompensationNumber,
)

fun SmeLegalComplianceDto?.toProgress() = listOf(
    this?.cipcAnnualReturns,
    this?.registration,
    this?.vatRegistration,
    this?.vatNumber,
    this?.taxComplianceStatus,
    this?.incomeTaxNumber,
    this?.workmanCompensationOption,
    this?.workmanCompensationNumber,
).toProgress()