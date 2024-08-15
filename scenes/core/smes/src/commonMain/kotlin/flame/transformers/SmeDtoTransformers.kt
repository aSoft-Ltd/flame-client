package flame.transformers

import cabinet.AttachmentDto
import cabinet.AttachmentPresenter
import cabinet.AttachmentPresenterOptions
import cabinet.toPresenter
import epsilon.MemorySize
import epsilon.memorySize
import flame.SmeDto
import flame.SmePresenter
import flame.SmeProgress
import flame.SmeSectionProgress
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

fun spreadsheetTemplate()  = AttachmentDto(
    uid = "0",
    name = "Investor_Readiness_Assessment_vs3.xlsx",
    type = "Investor_Readiness_Assessment",
    description = "Investor Readiness Assessment",
    size = memorySize("76000B"),
    url = "https://capital-downloads.picortex.co/Investor_Readiness_Assessment_vs3.xlsx"
)

fun SmeDto.toPresenter(options: AttachmentPresenterOptions) = SmePresenter(
    src = this,
    uid = uid,
    company = company,
    admin = admin?.toPresenter(),
    funding = funding,
    finance = finance,
    documents = documents.toPresenter(options),
    xlsx = xlsx?.toPresenter(options),
    governance = governance,
    plan = business,
    swot = swot,
    reports = reports.toList(),
    sheet = sheet
)


fun SmeDto.toProgress(options: AttachmentPresenterOptions) = SmeProgress(
    sme = toPresenter(options),
    admin = admin.toProgress(),
    documents = documents.toProgress(),
    finance = finance.toProgress(),
    analysis = SmeSectionProgress(xlsx?.let {
        1
    }?:0, 1),
    funding = funding.toProgress(),
    governance = governance.toProgress(),
    swot = swot.toProgress(),
    business = business.toProgress()
)