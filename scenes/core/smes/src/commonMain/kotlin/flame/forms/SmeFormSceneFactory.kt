package flame.forms

import flame.MonSmeScheme
import flame.OwnSmeScheme
import flame.SmeDto
import flame.SmePresenter
import flame.SmeSceneOptions
import symphony.Fields
import symphony.Form

internal fun <F : Fields<*>> SmeSceneOptions<OwnSmeScheme>.toFormScene(
    initializer: (SmeDto) -> Form<SmePresenter, *, F>
) = OwnSmeFormScene(this, initializer)

internal fun <F : Fields<*>> SmeSceneOptions<MonSmeScheme>.toFormScene(
    initializer: (SmeDto) -> Form<SmePresenter, *, F>
) = MonSmeFormScene(this, initializer)