@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.finance

import flame.SmeSectionProgress
import flame.finance.SmeBackOfficeDto
import flame.routes.financial.office.SmeBackOfficeOutput

inline fun SmeBackOfficeDto?.toOutput() = SmeBackOfficeOutput(
    accounting = this?.accounting,
    payroll = this?.payroll,
    accountingConsultation = this?.accountingConsultation,
    noOfEmployeesInTheFinanceDepartment = this?.noOfEmployeesInTheFinanceDepartment,
    financialHead = this?.financialHead,
    totalStaffCompliment = this?.totalStaffCompliment,
    hrConsultation = this?.hrConsultation,
    policyExistence = this?.policyExistence,
    policyReviewFrequency = this?.policyReviewFrequency,
    assetsAssurance = this?.assetsAssurance,
    criticalSystems = this?.criticalSystems,
)

inline fun SmeBackOfficeOutput.toParams() = SmeBackOfficeDto(
    accounting = accounting,
    payroll = payroll,
    accountingConsultation = accountingConsultation,
    noOfEmployeesInTheFinanceDepartment = noOfEmployeesInTheFinanceDepartment,
    financialHead = financialHead,
    totalStaffCompliment = totalStaffCompliment,
    hrConsultation = hrConsultation,
    policyExistence = policyExistence,
    policyReviewFrequency = policyReviewFrequency,
    assetsAssurance = assetsAssurance,
    criticalSystems = criticalSystems,
)

fun SmeBackOfficeDto?.toProgress(): SmeSectionProgress {
    val total = listOf(
        this?.accounting,
        this?.payroll,
        this?.accountingConsultation,
        this?.noOfEmployeesInTheFinanceDepartment,
        this?.financialHead,
        this?.totalStaffCompliment,
        this?.hrConsultation,
        this?.policyExistence,
        this?.policyReviewFrequency,
        this?.assetsAssurance,
        this?.criticalSystems,
    )
    val completed = total.filterNotNull()
    return SmeSectionProgress(completed.size, total.size)
}