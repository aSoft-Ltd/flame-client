@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.forms

import cinematic.LazyScene
import flame.SmePresenter
import kotlinx.JsExport
import symphony.Fields
import symphony.Form

abstract class XSmeFormScene<out F : Fields<*>> : LazyScene<Form<SmePresenter, *, F>>()