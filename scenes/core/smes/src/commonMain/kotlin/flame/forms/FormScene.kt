@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.forms

import cinematic.LazyScene
import kase.Pending
import kotlinx.JsExport
import symphony.Fields
import symphony.Form

abstract class FormScene<out F: Fields<*>> : LazyScene<Form<*,*,F>>(Pending)