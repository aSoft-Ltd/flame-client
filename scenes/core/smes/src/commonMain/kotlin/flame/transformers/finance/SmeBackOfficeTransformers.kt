@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.finance

import flame.SmeDto
import flame.finance.SmeBackOfficeDto
import flame.finance.SmeFinanceDto
import flame.forms.financial.office.SmeBackOfficeOutput
import flame.transformers.utils.toProgress
import kollections.listOf

internal inline fun SmeDto.toFinanceOfficeOutput() = finance?.office.toOutput(this)
internal inline fun SmeBackOfficeDto?.toOutput(src: SmeDto) = SmeBackOfficeOutput(
    src = src,
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

internal inline fun SmeBackOfficeOutput.toParams() = SmeBackOfficeDto(
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
).let {
    src.copy(finance = (src.finance ?: SmeFinanceDto()).copy(office = it))
}

internal fun SmeBackOfficeDto?.toProgress() = listOf(
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
).toProgress()