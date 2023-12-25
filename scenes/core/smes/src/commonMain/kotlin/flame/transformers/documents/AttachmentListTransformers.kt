package flame.transformers.documents

import cabinet.Attachment
import flame.SmeSectionProgress
import flame.documents.SmeDocument
import flame.documents.SmeDocumentListPresenter
import flame.documents.SmeDocumentsPresenter
import flame.documents.SmeFinancialRecordsPresenter
import flame.documents.SmeMiscellaneousDocumentsPresenter

internal fun Collection<Attachment>.toAttachment(document: SmeDocument) = firstOrNull { it.name.contains(document.label, ignoreCase = true) }
private fun Collection<Attachment>.toDocumentListPresenter() = SmeDocumentListPresenter(
    companyProfile = toAttachment(SmeDocument.List.CompanyProfile),
    businessPlan = toAttachment(SmeDocument.List.BusinessPlan),
    companyRegistrationDocument = toAttachment(SmeDocument.List.CompanyRegistrationDocument),
    shareholderRegister = toAttachment(SmeDocument.List.ShareholderRegister),
    taxPin = toAttachment(SmeDocument.List.TaxPin),
    fundingApplicationRequest = toAttachment(SmeDocument.List.FundingApplicationRequest),
    beeAffidavit = toAttachment(SmeDocument.List.BeeAffidavit),
    pithDeck = toAttachment(SmeDocument.List.PithDeck),
)

private fun Collection<Attachment>.toFinancialRecordsPresenter() = SmeFinancialRecordsPresenter(
    balanceSheet = toAttachment(SmeDocument.FinancialRecord.BalanceSheet),
    incomeStatement = toAttachment(SmeDocument.FinancialRecord.IncomeStatement),
    cashFlow = toAttachment(SmeDocument.FinancialRecord.CashFlow),
    assetRegister = toAttachment(SmeDocument.FinancialRecord.AssetRegister),
    financialProjections = toAttachment(SmeDocument.FinancialRecord.FinancialProjections),
    salesPipeline = toAttachment(SmeDocument.FinancialRecord.SalesPipeline),
)

private fun Collection<Attachment>.toMiscellaneousPresenter() = SmeMiscellaneousDocumentsPresenter(
    letterOfIntent = toAttachment(SmeDocument.Additional.LetterOfIntent),
    quotations = toAttachment(SmeDocument.Additional.Quotations),
    mouOrSaleOfAgreements = toAttachment(SmeDocument.Additional.MouOrSaleOfAgreements),
)

internal fun Collection<Attachment>.toPresenter() = SmeDocumentsPresenter(
    list = toDocumentListPresenter(),
    financials = toFinancialRecordsPresenter(),
    additional = toMiscellaneousPresenter()
)

internal fun Collection<Attachment>.toProgress(): SmeSectionProgress {
    val total = SmeDocument.List.entries + SmeDocument.FinancialRecord.entries + SmeDocument.Additional.entries
    return SmeSectionProgress(size, total.size)
}