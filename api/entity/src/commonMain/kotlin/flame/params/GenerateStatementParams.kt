package flame.params

import kotlinx.serialization.Serializable
import krono.LocalDate

@Serializable
data class GenerateStatementParams(
    val customerId: String,
    val start: LocalDate,
    val end: LocalDate,
)