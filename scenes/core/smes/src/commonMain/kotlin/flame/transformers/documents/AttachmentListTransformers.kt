package flame.transformers.documents

import cabinet.AttachmentDto
import cabinet.AttachmentPresenterOptions
import cabinet.toPresenter
import flame.SmeSectionProgress
import flame.documents.SmeDocument
import flame.documents.SmeDocumentListPresenter
import flame.documents.SmeDocumentsPresenter
import flame.documents.SmeFinancialRecordsPresenter
import flame.documents.SmeMiscellaneousDocumentsPresenter

internal fun Collection<AttachmentDto>.toAttachment(
    document: SmeDocument,
    options: AttachmentPresenterOptions
) = firstOrNull { it.name.contains(document.label, ignoreCase = true) }?.toPresenter(options)

private fun Collection<AttachmentDto>.toDocumentListPresenter(
    options: AttachmentPresenterOptions
) = SmeDocumentListPresenter(
    companyProfile = toAttachment(SmeDocument.List.CompanyProfile,options),
    businessPlan = toAttachment(SmeDocument.List.BusinessPlan,options),
    companyRegistrationDocument = toAttachment(SmeDocument.List.CompanyRegistrationDocument,options),
    shareholderRegister = toAttachment(SmeDocument.List.ShareholderRegister,options),
    taxPin = toAttachment(SmeDocument.List.TaxPin,options),
    fundingApplicationRequest = toAttachment(SmeDocument.List.FundingApplicationRequest,options),
    beeAffidavit = toAttachment(SmeDocument.List.BeeAffidavit,options),
    pithDeck = toAttachment(SmeDocument.List.PithDeck,options),
)

private fun Collection<AttachmentDto>.toFinancialRecordsPresenter(options: AttachmentPresenterOptions) = SmeFinancialRecordsPresenter(
//    balanceSheet = toAttachment(SmeDocument.FinancialRecord.BalanceSheet, options),
//    incomeStatement = toAttachment(SmeDocument.FinancialRecord.IncomeStatement, options),
//    cashFlow = toAttachment(SmeDocument.FinancialRecord.CashFlow, options),
    currentYearFinancials = toAttachment(SmeDocument.FinancialRecord.CurrentYearFinancials, options),
    priorYearFinancialStatementYear1 = toAttachment(SmeDocument.FinancialRecord.PriorYearFinancialStatementYear1, options),
    priorYearFinancialStatementYear2 = toAttachment(SmeDocument.FinancialRecord.PriorYearFinancialStatementYear2, options),
    assetRegister = toAttachment(SmeDocument.FinancialRecord.AssetRegister, options),
    financialProjections = toAttachment(SmeDocument.FinancialRecord.FinancialProjections, options),
    salesPipeline = toAttachment(SmeDocument.FinancialRecord.SalesPipeline, options),
)

private fun Collection<AttachmentDto>.toMiscellaneousPresenter(
    options: AttachmentPresenterOptions
) = SmeMiscellaneousDocumentsPresenter(
    letterOfIntent = toAttachment(SmeDocument.Additional.LetterOfIntent,options),
    quotations = toAttachment(SmeDocument.Additional.Quotations,options),
    mouOrSaleOfAgreements = toAttachment(SmeDocument.Additional.MouOrSaleOfAgreements,options),
)

internal fun Collection<AttachmentDto>.toPresenter(
    options: AttachmentPresenterOptions
) = SmeDocumentsPresenter(
    list = toDocumentListPresenter(options),
    financials = toFinancialRecordsPresenter(options),
    additional = toMiscellaneousPresenter(options)
)

internal fun Collection<AttachmentDto>.toProgress(): SmeSectionProgress {
    val total = SmeDocument.List.entries + SmeDocument.FinancialRecord.entries + SmeDocument.Additional.entries
    return SmeSectionProgress(size, total.size)
}