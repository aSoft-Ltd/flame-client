@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.swot

import cinematic.LazyScene
import flame.SmePresenter
import flame.SmeSceneOptions
import flame.SmeScheme
import flame.forms.swot.SmeSwotFields
import flame.forms.swot.SmeSwotOutput
import flame.forms.swot.SwotComponent
import flame.forms.swot.SwotComponent.opportunity
import flame.forms.swot.SwotComponent.strength
import flame.forms.swot.SwotComponent.threat
import flame.forms.swot.SwotComponent.weakness
import flame.swot.SmeSwotDto
import kollections.List
import kollections.add
import kollections.indexOf
import kollections.minus
import kollections.plus
import kollections.remove
import kollections.toMutableList
import koncurrent.Later
import koncurrent.later.andThen
import koncurrent.later.then
import koncurrent.toLater
import kotlinx.JsExport
import neat.required
import symphony.Confirm
import symphony.Peekaboo
import symphony.toForm

abstract class SmeSwotComponentScene(
    private val options: SmeSceneOptions<SmeScheme>,
    private val component: SwotComponent
) : LazyScene<List<String>>() {

    protected var presenter: SmePresenter? = null

    val form = Peekaboo { input: String? ->
        val label = if (input != null) "Edit" else "Add"
        SmeSwotFields(component, SmeSwotOutput(input)).toForm(
            heading = "SWOT analysis form",
            details = "$label a $component",
            logger = options.logger
        ) {
            onSubmit("$label $component") { output ->
                output.toLater().then {
                    it::input.required
                }.andThen {
                    val payload = if (input == null) {
                        existing + it
                    } else {
                        val new = existing.toMutableList()
                        val idx = new.indexOf(input)
                        new.add(idx, it)
                        new.remove(input)
                        new
                    }
                    val sme = presenter?.src ?: return@andThen Later("Initialize this scene please")
                    val swot = sme.swot ?: SmeSwotDto()
                    val expected = sme.copy(
                        swot = when(component){
                            strength -> swot.copy(strengths = payload)
                            weakness -> swot.copy(weaknesses = payload)
                            threat -> swot.copy(threats = payload)
                            opportunity -> swot.copy(opportunities = payload)
                        }
                    )
                    options.api.update(expected)
                }
            }
            onSuccess { refresh(dispatch = true) }
        }
    }

    val confirm = Confirm { swot: String ->
        heading = "Delete a $component"
        details = "Are you sure you want to do this?"
        message = "Deleting, please wait . . ."
        onConfirm("Delete") {
            val sme = presenter?.src ?: return@onConfirm Later("Initialize this scene please")
            val payload = existing - swot
            val dto = sme.swot ?: SmeSwotDto()
            val expected = sme.copy(
                swot = when(component){
                    strength -> dto.copy(strengths = payload)
                    weakness -> dto.copy(weaknesses = payload)
                    threat -> dto.copy(threats = payload)
                    opportunity -> dto.copy(opportunities = payload)
                }
            )
            options.api.update(expected).then {
                refresh(dispatch = true)
            }
        }
    }
    private val existing get() = ui.value.data ?: throw RuntimeException("Can't pull data if it is not loaded")

    abstract fun refresh(dispatch: Boolean)

    fun showAddForm() = form.show(null)
    fun showEditForm(item: String) = form.show(item)

    fun showDelete(item: String) = confirm.show(item)
}