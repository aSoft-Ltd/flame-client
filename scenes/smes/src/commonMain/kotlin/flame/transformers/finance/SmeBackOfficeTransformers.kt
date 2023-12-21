@file:Suppress("NOTHING_TO_INLINE")

package flame.transformers.finance

import flame.SmeApi
import flame.SmeSceneOption
import flame.finance.SmeBackOfficeDto
import flame.routes.financial.office.SmeBackOfficeFields
import flame.routes.financial.office.SmeBackOfficeOutput
import flame.transformers.utils.toProgress
import kollections.listOf
import koncurrent.toLater
import symphony.toForm

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

inline fun SmeBackOfficeDto?.toForm(options: SmeSceneOption<SmeApi>) = SmeBackOfficeFields(toOutput()).toForm(
    heading = "Business Details",
    details = "Enter your business details here",
    logger = options.logger,
) {
    onSubmit { output ->
        output.toLater().then {
            output.toParams()
        }.andThen {
            options.api.finance.update(it)
        }
    }
    onSuccess {
        options.bus.dispatch(options.topic.progressMade())
    }
}

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

fun SmeBackOfficeDto?.toProgress() = listOf(
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