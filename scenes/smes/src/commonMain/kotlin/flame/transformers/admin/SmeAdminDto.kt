package flame.transformers.admin

import flame.SmeSectionProgress
import flame.admin.SmeAdminDto
import flame.admin.SmeAdminPresenter
import flame.transformers.utils.aggregate
import flame.transformers.utils.toCompletedIfNotEmpty

fun SmeAdminDto?.toProgress(): SmeSectionProgress = listOf(
    this?.contacts.toProgress(),
    this?.business.toProgress(),
    this?.legal.toProgress(),
    this?.directors.toCompletedIfNotEmpty(),
    this?.shareholders.toCompletedIfNotEmpty(),
).aggregate()

fun SmeAdminDto.toPresenter() = SmeAdminPresenter(
    contacts = contacts,
    business = business?.toPresenter(),
    legal = legal,
    directors = directors,
    shareholders = shareholders,
)