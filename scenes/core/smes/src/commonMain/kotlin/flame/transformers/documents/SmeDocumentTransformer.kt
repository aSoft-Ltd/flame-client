package flame.transformers.documents

import cabinet.AttachmentPresenterOptions
import flame.SmeDto
import flame.documents.SmeDocument

internal fun SmeDocument.toAttachment(
    sme: SmeDto,
    options: AttachmentPresenterOptions
) = sme.documents.toAttachment(this, options)