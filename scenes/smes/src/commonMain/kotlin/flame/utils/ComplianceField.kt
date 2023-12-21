package flame.utils

import kotlin.reflect.KMutableProperty0
import symphony.Fields
import symphony.Option
import symphony.selectSingle
import kollections.listOf

internal fun Fields<*>.selectCompliance(
    name: KMutableProperty0<String?>,
    label: String,
    hint: String,
) = selectSingle(
    name = name,
    label = label,
    hint = hint,
    items = listOf("Compliant", "Non-Compliant"),
    mapper = { Option(it, it) }
)