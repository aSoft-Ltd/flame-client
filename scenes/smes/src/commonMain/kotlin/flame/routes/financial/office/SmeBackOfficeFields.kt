@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.financial.office

import flame.utils.selectSourcing
import flame.utils.selectYesOrNo
import kotlin.js.JsExport
import symphony.Fields
import symphony.Option
import kollections.listOf
import symphony.integer
import symphony.selectSingle
import symphony.text

class SmeBackOfficeFields(output: SmeBackOfficeOutput) : Fields<SmeBackOfficeOutput>(output) {

    val accounting = selectSingle(
        name = output::accounting,
        label = "Please provide the name of the accounting system in place",
        items = listOf(
            "Sage Business Cloud",
            "Sage Pastel",
            "Sage Evolution",
            "Sage 50",
            "Zero",
            "Quickbooks",
            "Zoho",
            "FreshBooks",
            "Wave Accounting",
            "Excel",
            "Other"
        ),
        mapper = { Option(it, it) }
    )

    val payroll = selectSingle(
        name = output::payroll,
        label = "Please provide the name of the payroll system in place",
        items = listOf(
            "Sage Payroll",
            "Sage VIP",
            "Simple Pay",
            "GreatSoft",
            "PSiber",
            "Softline Pastel Payroll",
            "Accsys",
            "Ramco Payroll Management",
            "Excel",
            "Other"
        ),
        mapper = { Option(it, it) }
    )

    val accountingConsultation = selectSourcing(
        name = output::accountingConsultation,
        label = "Are the accounting and finance functions performed internally or by external consultants?"
    )

    val noOfEmployeesInTheFinanceDepartment = integer(
        name = output::noOfEmployeesInTheFinanceDepartment,
        label = "If finance functions are performed internally how many people are working in the finance department?",
        hint = "Enter Number of People in Finance Department"
    )

    val financialHead = selectYesOrNo(
        name = output::financialHead,
        label = "Does the entity have a financial manager and or financial director?"
    )

    val totalStaffCompliment = integer(
        name = output::totalStaffCompliment,
        label = "What is the total staff compliment of the company?",
        hint = "Enter Total Staff Compliment"
    )

    val hrConsultation = selectSourcing(
        name = output::hrConsultation,
        label = """Are the HR functions outsources or inhouse? 
            |(Such as Recruitment and Selection, Training and Development, 
            |Performance Management, Employee Relations, Employment Law and Compliance,
            | Compensation and Benefits and Administration, Payroll & HR Systems)""".trimMargin()
    )

    val policyExistence = text(
        name = output::policyExistence,
        label = "Does the entity have policies and procedures in place (i.e. finance, HR, operations, etc)"
    )

    val policyReviewFrequency = selectSingle(
        name = output::policyReviewFrequency,
        label = "If Yes how often are these policies and procedures reviewed?",
        items = listOf("Monthly", "Quarterly", "Semi-annually", "Annually", "Never"),
        mapper = { Option(it, it) }
    )

    val assetsAssurance = selectYesOrNo(
        name = output::assetsAssurance,
        label = "Are all company assets adequately insured?"
    )

    val criticalSystems = text(
        name = output::criticalSystems,
        label = "What other critical system are in place to aid the back office functions?"
    )
}