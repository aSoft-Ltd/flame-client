package flame.transformers

import cabinet.AttachmentPresenterOptions
import epsilon.FileManager
import flame.SmeDto
import flame.SmePresenter
import flame.SmeProgress
import flame.SmeSceneOptions
import flame.transformers.admin.toPresenter
import flame.transformers.admin.toProgress
import flame.transformers.documents.toPresenter
import flame.transformers.documents.toProgress
import flame.transformers.finance.toProgress
import flame.transformers.funding.toProgress
import flame.transformers.governance.toProgress
import flame.transformers.swot.toProgress
import io.ktor.client.HttpClient
import klip.Clipboard
import kotlinx.coroutines.CoroutineScope
import sentinel.UserSession

fun SmeDto.toPresenter(options: AttachmentPresenterOptions) = SmePresenter(
    src = this,
    uid = uid,
    company = company,
    admin = admin?.toPresenter(),
    funding = funding,
    finance = finance,
    documents = documents.toPresenter(options),
    swot = swot,
)

fun SmeDto.toProgress(options: AttachmentPresenterOptions) = SmeProgress(
    sme = toPresenter(options),
    admin = admin.toProgress(),
    documents = documents.toProgress(),
    finance = finance.toProgress(),
    funding = funding.toProgress(),
    governance = governance.toProgress(),
    swot = swot.toProgress()
)