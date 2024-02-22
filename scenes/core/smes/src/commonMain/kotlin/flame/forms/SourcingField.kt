package flame.forms

import kotlin.reflect.KMutableProperty0
import symphony.Fields
import symphony.Option
import symphony.selectSingle
import kollections.listOf

internal fun Fields<*>.selectSourcing(
    name: KMutableProperty0<String?>,
    label: String,
) = selectSingle(
    name = name,
    label = label,
    items = listOf(
        "In house",
        "Outsourced"
    ),
    mapper = { Option(it, it) }
)