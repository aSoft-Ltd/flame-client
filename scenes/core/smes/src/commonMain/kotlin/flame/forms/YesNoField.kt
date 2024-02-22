package flame.forms

import kotlin.reflect.KMutableProperty0
import symphony.Fields
import symphony.Option
import symphony.selectSingle
import kollections.listOf

fun Fields<*>.selectYesOrNo(
    name: KMutableProperty0<String?>,
    label: String
) = selectSingle(
    name = name,
    label = label,
    items = listOf("Yes", "No"),
    mapper = { Option(it, it) }
)