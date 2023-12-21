@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.swot

import cinematic.LazyScene
import flame.SmeApi
import flame.SmeDto
import flame.SmeSceneOption
import kollections.plus
import kollections.toMutableList
import flame.SmeSwotComponentApi
import kase.Loading
import kase.toLazyState
import kollections.List
import kollections.add
import kollections.indexOf
import kollections.minus
import kollections.remove
import koncurrent.later.finally
import koncurrent.toLater
import kotlin.js.JsExport
import neat.required
import symphony.Confirm
import symphony.Peekaboo
import symphony.toForm

abstract class SmeSwotComponentScene(private val options: SmeSceneOption<SmeApi>) : LazyScene<List<String>>() {

    protected abstract val api: SmeSwotComponentApi
    protected abstract val component: String

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
                    api.update(payload)
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
            api.update(existing - swot).then {
                refresh(dispatch = true)
            }
        }
    }
    private val existing get() = ui.value.data ?: throw RuntimeException("Can't pull data if it is not loaded")

    fun initialize() {
        ui.value = Loading("Fetching information, please wait . . .")
        refresh(false)
    }

    fun refresh(dispatch: Boolean = true) {
        options.api.load().then {
            it.toList()
        }.finally {
            ui.value = it.toLazyState()
        }
        form.show(null)

        if (!dispatch) return
        options.bus.dispatch(options.topic.progressMade())
    }

    fun showAddForm() = form.show(null)
    fun showEditForm(item: String) = form.show(item)

    fun showDelete(item: String) = confirm.show(item)

    protected abstract fun SmeDto.toList(): List<String>
}