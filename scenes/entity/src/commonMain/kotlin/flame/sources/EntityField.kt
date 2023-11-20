@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.sources

import cinematic.mutableLiveOf
import identifier.LegalEntityPresenter
import kollections.iEmptyList
import neat.ValidationFactory
import neat.Validity
import neat.custom
import symphony.Changer
import symphony.Feedbacks
import symphony.Field
import symphony.Visibility
import symphony.internal.AbstractHideable
import symphony.properties.Settable
import symphony.toErrors
import symphony.toWarnings
import kotlin.js.JsExport
import kotlin.reflect.KMutableProperty0

abstract class EntityField(
    private val property: KMutableProperty0<LegalEntityPresenter?>,
    label: String,
    visibility: Visibility,
    private val onChange: Changer<LegalEntityPresenter>?,
    factory: ValidationFactory<LegalEntityPresenter>?,
) : AbstractHideable(), Field<LegalEntityPresenter, EntityFieldState>, Settable<LegalEntityPresenter> {

    protected val validator = custom<LegalEntityPresenter>(label).configure(factory)

    override fun set(value: LegalEntityPresenter?) {
        val res = validator.validate(value)
        property.set(res.value)
        state.value = state.value.copy(
            output = res.value,
            feedbacks = Feedbacks(res.toWarnings())
        )
        onChange?.invoke(value)
    }

    fun switchToMaking() {
        val mode = state.value.mode
        if (mode is EntityMakingMode) return
        if (mode is EntityPickingMode) {
            mode.deInitialize(false)
            state.value = state.value.copy(mode = making())
        }
    }

    fun switchToPicking() {
        val mode = state.value.mode
        if (mode is EntityPickingMode) return
        if (mode is EntityMakingMode) {
            mode.deInitialize()
            state.value = state.value.copy(mode = picking())
        }
    }

    protected abstract fun picking(): EntityPickingMode

    protected abstract fun making(): EntityMakingMode

    override fun finish() {
        val mode = state.value.mode
        if (mode is EntityPickingMode) {
            mode.deInitialize(true)
        }
        if (mode is EntityMakingMode) {
            mode.ui.value.data?.exit()
        }
    }

    private val initial by lazy {
        EntityFieldState(
            output = property.get(),
            feedbacks = Feedbacks(iEmptyList()),
            required = false,
            visibility = visibility,
            mode = picking()
        )
    }

    override val state by lazy { mutableLiveOf(initial) }

    override fun validate() = validator.validate(output)

    override fun validateToErrors(): Validity<LegalEntityPresenter> {
        val res = validator.validate(output)
        val errors = res.toErrors()
        if (errors.isNotEmpty()) {
            state.value = state.value.copy(feedbacks = Feedbacks(errors))
        }
        return res
    }

    override fun setVisibility(v: Visibility) {
        state.value = state.value.copy(visibility = v)
    }

    override fun clear() = set(null)

    override fun reset() = set(initial.output)

    val mode get() = state.value.mode
    override val output get() = state.value.output
    override val required get() = state.value.required
    override val feedbacks get() = state.value.feedbacks
    override val visibility get() = state.value.visibility
}