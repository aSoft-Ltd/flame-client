package flame.transformers

import cabinet.AttachmentPresenterOptions
import flame.SmeDto
import flame.SmePresenter
import flame.SmeProgress
import flame.transformers.admin.toPresenter
import flame.transformers.admin.toProgress
import flame.transformers.documents.toPresenter
import flame.transformers.documents.toProgress
import flame.transformers.finance.toProgress
import flame.transformers.funding.toProgress
import flame.transformers.governance.toProgress
import flame.transformers.swot.toProgress
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
    reports = reports.toList()
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