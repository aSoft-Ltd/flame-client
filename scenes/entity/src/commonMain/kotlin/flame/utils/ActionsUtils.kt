package flame.utils

import captain.NavigateFunction
import flame.EntityDestination
import kevlar.builders.Actions0Builder

internal fun Actions0Builder<Unit>.customerActions(navigateTo: NavigateFunction, from: String) {
    val destination = EntityDestination(from)
    on("Generate Statement") { navigateTo(destination.generateStatement) }
    on("Send Message") { navigateTo(destination.sendMessage) }
    onEdit { navigateTo(destination.edit) }
    onDuplicate { navigateTo(destination.duplicate) }
}