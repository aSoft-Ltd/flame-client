@file:JsExport

package flame.route.ratios.pnl

import cinematic.BaseScene
import flame.SmeSceneOption
import flame.XSmeScheme
import flame.route.ratios.utils.SmeStatements
import kollections.forEach
import kollections.listOf
import kollections.map
import kollections.sum
import kotlinx.JsExport
import symphony.SpreadSheet
import symphony.buildTemplate
import kotlin.math.floor

class SpreadSheets(private val options: SmeSceneOption<XSmeScheme>) : BaseScene() {

    private val columns = SmeStatements.get(options.clock).all.map { year ->
        val millennium = 1000 * floor(year.toDouble() / 1000).toInt()
        "${year}/${(year + 1) - millennium}"
    }

    val pnl = SpreadSheet(
        template = buildTemplate {
            captured("Revenue", bold = true)
            captured("Cost of Sales")
            computed("Gross Profit", bold = true) { cell ->
                cell("Revenue") - cell("Cost of Sales")
            }

            val expenses = listOf(
                "Administrative Expenses",
                "Other Operating Expenses",
                "Salary & Staff Cost",
            )
            group("Expenses", bold = true) { expenses.forEach { captured(it) } }

            val ebitda = listOf(
                "Interest Income",
                "Finances Cost",
                "Depreciation & Amortization",
            )
            group("EBITDA", bold = true) { ebitda.forEach { captured(it) } }

            computed("Profit Before Tax", bold = true) { cell ->
                val gp = cell("Revenue") - cell("Cost of Sales")
                val exp = expenses.map(cell).sum()
                val ebi = ebitda.map(cell).sum()
                gp - exp - ebi
            }
            captured("Income Tax Expense")
            computed("Profit/Loss", bold = true) { cell ->
                cell("Profit Before Tax") - cell("Income Tax Expense")
            }
        },
        columns = columns
    )

    val bs = SpreadSheet(
        template = buildTemplate {
            val nca = listOf(
                "Property Plant & Equipment",
                "Intangible Assets",
                "Long Term Investments",
                "Deferred Tax Assets",
            )
            group("Non-Current Assets", bold = true) { nca.forEach { captured(it) } }

            val ca = listOf(
                "Cash & Cash Equivalent",
                "Trade Receivables",
                "Inventory",
                "Investments",
                "Assets Help for Sale",
            )
            group("Current Assets", bold = true) { ca.forEach { captured(it) } }

            computed("Total Assets", bold = true) { cell ->
                nca.map(cell).sum() + ca.map(cell).sum()
            }

            val equities = listOf(
                "Share Capital",
                "Retained Earnings",
                "Shareholders Loan",
                "Non-distributable Reserves",
            )
            group("Equities", bold = true) { equities.forEach { captured(it) } }

            val long_liabilities = listOf(
                "Long Term Portion of Loan",
                "Deferred Tax Liability",
            )
            group("Liabilities", bold = true) { long_liabilities.forEach { captured(it) } }

            val curr_liabilities = listOf(
                "Short Term Portion of Loan",
                "Tax Payables",
                "Trade Payables",
            )
            group("Current Liabilities", bold = true) { curr_liabilities.forEach { captured(it) } }
            computed("Total Equities & Liabilities", bold = true) { cell ->
                val eq = equities.map(cell).sum()
                val longLiabilities = long_liabilities.map(cell).sum()
                val currLiabilities = curr_liabilities.map(cell).sum()

                eq + longLiabilities + currLiabilities
            }
        },
        columns = columns
    )

    val cf = SpreadSheet(
        template = buildTemplate {
            val cffoa = listOf(
                "Cash receipts from customers",
                "Cash paid to suppliers and employees",
            )
            group("Cash flows from operating activities", bold = true) {
                cffoa.forEach { captured(it) }
            }

            val cgfo = listOf(
                "Interested Received",
                "Interest Paid",
                "Income taxes paid",
            )
            group("Cash generated from operations", bold = true) {
                cgfo.forEach { captured(it) }
            }

            computed("Net cash from operating activities", bold = true) { cell ->
                cffoa.map(cell).sum() + cgfo.map(cell).sum()
            }

            val cfffa = listOf(
                "Proceeds from issue of share capital (Equity Investor)",
                "Proceeds from long-term borrowings (Debt Investor)",
                "Shareholder Loans (Repayments)",
                "Capital Repayments of Loans",
            )
            group("Cash flows from financing activities", bold = true) {
                cfffa.forEach { captured(it) }
            }

            computed("Net cash used in financing activities") { cell ->
                cfffa.map(cell).sum()
            }

            computed("Net increase in cash and cash equivalents") { cell ->
                cfffa.map(cell).sum()
            }

            computed("Cash and cash equivalents at the beginning of the period") { cell ->
                0.0
            }
            computed("Cash and cash equivalents at the end of the period") { cell ->
                0.0
            }
        },
        columns = columns
    )

    val ratios = SpreadSheet(
        template = buildTemplate {
            captured("Return on Equity (ROE)")
            captured("Debt Equity Ratio (Total liabilities)")
            computed("Liquidity Ratio") { 0.0 }
            computed("Current Ratio") { 0.0 }
            captured("Acid Test Ratio (Quick Ratio)")
            captured("Debtors Days")
            computed("Equity Investment Value") { 0.0 }
            captured("Return on Investment (ROI)")
            captured("Sales Growth")
            captured("Gross profit margin")
            captured("Cost to Income ratio")
            captured("Operating margin. (EBITDA)")
            captured("Interest Cover Ratio")
            captured("Net Operating Profit Margin")
        },
        columns = columns
    )
}