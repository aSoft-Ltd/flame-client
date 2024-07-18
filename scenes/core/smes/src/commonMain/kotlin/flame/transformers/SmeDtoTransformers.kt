package flame.transformers

import cabinet.AttachmentPresenterOptions
import flame.SmeDto
import flame.SmePresenter
import flame.SmeProgress
import flame.sheet.SmeSheet
import flame.sheet.SmeSheetCell
import flame.sheet.SmeSheetCellAlign
import flame.sheet.SmeSheetRow
import flame.transformers.admin.toPresenter
import flame.transformers.admin.toProgress
import flame.transformers.documents.toPresenter
import flame.transformers.documents.toProgress
import flame.transformers.finance.toProgress
import flame.transformers.funding.toProgress
import flame.transformers.governance.toProgress
import flame.transformers.swot.toProgress
import kollections.isEmpty
import kollections.toList

fun SmeDto.toPresenter(options: AttachmentPresenterOptions) = SmePresenter(
    src = this,
    uid = uid,
    company = company,
    admin = admin?.toPresenter(),
    funding = funding,
    finance = finance,
    documents = documents.toPresenter(options),
    governance = governance,
    plan = business,
    swot = swot,
    reports = reports.toList(),
    sheet = sheet.orTest()
)

private fun SmeSheet?.orTest():SmeSheet {
    return this?.let {
        if (this.rows.isEmpty()) {
            testSheet()
        } else {
            this
        }
    }?: testSheet()
}

private fun testSheet() = SmeSheet(
    rows = kollections.listOf(
        SmeSheetRow(cells = kollections.listOf(
            SmeSheetCell(""),
            SmeSheetCell("2023", bold = true),
            SmeSheetCell("2024", bold = true),
            SmeSheetCell("2025", bold = true),
            SmeSheetCell("2026", bold = true),
            SmeSheetCell("2027", bold = true),
        )),
        SmeSheetRow(cells = kollections.listOf(
            SmeSheetCell("Revenue", bold = true),
            SmeSheetCell(""),
            SmeSheetCell(""),
            SmeSheetCell(""),
            SmeSheetCell(""),
            SmeSheetCell(""),
        )),
        SmeSheetRow(cells = kollections.listOf(
            SmeSheetCell("Other", indent = true),
            SmeSheetCell("0", align = SmeSheetCellAlign.center),
            SmeSheetCell("0", align = SmeSheetCellAlign.center),
            SmeSheetCell("0", align = SmeSheetCellAlign.center),
            SmeSheetCell("0", align = SmeSheetCellAlign.center),
            SmeSheetCell("0", align = SmeSheetCellAlign.center),
        )),
        SmeSheetRow(cells = kollections.listOf(
            SmeSheetCell("Another", indent = true),
            SmeSheetCell("12,000", align = SmeSheetCellAlign.center),
            SmeSheetCell("200", align = SmeSheetCellAlign.center),
            SmeSheetCell("1,600", align = SmeSheetCellAlign.center),
            SmeSheetCell("200", align = SmeSheetCellAlign.center),
            SmeSheetCell("10", align = SmeSheetCellAlign.center),
        )),
        SmeSheetRow(cells = kollections.listOf(
            SmeSheetCell("Total Revenue", bold = true),
            SmeSheetCell("", bold = true),
            SmeSheetCell("", bold = true),
            SmeSheetCell("", bold = true),
            SmeSheetCell("", bold = true),
            SmeSheetCell("", bold = true),
        )),
        SmeSheetRow(cells = kollections.listOf(
            SmeSheetCell("Cost of Goods Sold", bold = true),
            SmeSheetCell(""),
            SmeSheetCell(""),
            SmeSheetCell(""),
            SmeSheetCell(""),
            SmeSheetCell(""),
        )),
    )
)

fun SmeDto.toProgress(options: AttachmentPresenterOptions) = SmeProgress(
    sme = toPresenter(options),
    admin = admin.toProgress(),
    documents = documents.toProgress(),
    finance = finance.toProgress(),
    funding = funding.toProgress(),
    governance = governance?.manpower.toProgress(),
    swot = swot.toProgress()
)