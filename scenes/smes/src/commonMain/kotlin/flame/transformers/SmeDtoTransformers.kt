package flame.transformers

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


fun SmeDto.toPresenter() = SmePresenter(
    src = this,
    uid = uid,
    company = company,
    admin = admin?.toPresenter(),
    funding = funding,
    finance = finance,
    documents = documents.toPresenter(),
    swot = swot,
)

fun SmeDto.toProgress() = SmeProgress(
    sme = toPresenter(),
    admin = admin.toProgress(),
    documents = documents.toProgress(),
    finance = finance.toProgress(),
    funding = funding.toProgress(),
    governance = governance.toProgress(),
    swot = swot.toProgress()
)