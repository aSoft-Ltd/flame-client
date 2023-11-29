package flame.transformers

import flame.SmeDto
import flame.SmePresenter
import flame.SmeProgress
import flame.transformers.admin.toProgress
import flame.transformers.documents.toPresenter
import flame.transformers.documents.toProgress
import flame.transformers.funding.toProgress


fun SmeDto.toPresenter() = SmePresenter(
    src = this,
    uid = uid,
    company = company,
    admin = admin,
    funding = funding,
    finance = finance,
    documents = documents.toPresenter()
)
fun SmeDto.toProgress() = SmeProgress(
    sme = toPresenter(),
    admin = admin.toProgress(),
    funding = funding.toProgress(),
    documents = documents.toProgress()
)