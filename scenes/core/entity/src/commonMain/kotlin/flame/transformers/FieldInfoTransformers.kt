package flame.transformers

import identifier.FieldInfo
import identifier.fields.AdditionalField
import identifier.fields.AdditionalInfoOutput
import kollections.List
import kollections.map
import kollections.toList
import krono.LocalDateField
import symphony.BooleanField
import symphony.DoubleField
import symphony.Field
import symphony.IntField
import symphony.Option
import symphony.SingleChoiceField
import symphony.TextField

fun FieldInfo.toField(output:AdditionalInfoOutput):Field<*, *> = when (this.category) {
    FieldInfo.Category.FREE_TEXT -> TextField(name, label=label, onChange=onChange(output))
    FieldInfo.Category.DATE -> LocalDateField(name, label=label, onChange=onChange(output))
    FieldInfo.Category.TIME -> TODO("Time inputs not supported yet")
    FieldInfo.Category.NUMBER -> DoubleField(name, label=label, onChange=onChange(output))
    FieldInfo.Category.CHOICE -> SingleChoiceField(
        name=name,
        items = options?.toList() ?: kollections.emptyList(),
        mapper = {
             Option(label=it.label, value = it.value)
        },
        onChange=onChange(output)
    )
    FieldInfo.Category.INTEGER -> IntField(name, label=label, onChange=onChange(output))
    FieldInfo.Category.BOOLEAN -> BooleanField(name, label=label, onChange=onChange(output))
}

fun List<FieldInfo>.toFields(output:AdditionalInfoOutput) = this.map {
    AdditionalField(it.toField(output), it)
}

private fun <T> FieldInfo.onChange(output: AdditionalInfoOutput):(T)->Unit = {
    output.set(name, it)
}