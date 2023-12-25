package flame.transformers.documents

import flame.SmeDto
import flame.documents.SmeDocument

internal fun SmeDocument.toAttachment(sme: SmeDto) = sme.documents.toAttachment(this)