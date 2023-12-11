@file:JsExport

package flame.sources

import identifier.LegalEntityPresenter
import kotlinx.JsExport
import symphony.Feedbacks
import symphony.FieldState
import symphony.Visibility

data class EntityFieldState(
    override val output: LegalEntityPresenter?,
    override val required: Boolean,
    override val visibility: Visibility,
    override val feedbacks: Feedbacks,
    val mode: EntityFieldMode
) : FieldState<LegalEntityPresenter>