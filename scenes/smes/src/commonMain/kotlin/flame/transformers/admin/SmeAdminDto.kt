package flame.transformers.admin

import flame.SmeSectionProgress
import flame.admin.SmeAdminDto
import flame.transformers.utils.aggregate
import flame.transformers.utils.toProgress

fun SmeAdminDto?.toProgress(): SmeSectionProgress = listOf(
    this?.contacts.toProgress(),
    this?.business.toProgress(),
    this?.legal.toProgress(),
    this?.directors.toProgress(),
    this?.shareholders.toProgress(),
).aggregate()

