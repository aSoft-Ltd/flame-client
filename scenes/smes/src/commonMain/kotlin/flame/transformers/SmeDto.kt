package flame.transformers

import flame.SmeDto
import flame.SmeProgress
import flame.transformers.admin.toProgress
import flame.transformers.funding.toProgress

fun SmeDto.toProgress() = SmeProgress(
    sme = this,
    admin = admin.toProgress(),
    funding = funding.toProgress()
)