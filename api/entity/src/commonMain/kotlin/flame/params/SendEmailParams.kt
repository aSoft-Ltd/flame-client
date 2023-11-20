package flame.params

import kotlinx.serialization.Serializable

@Serializable
data class SendEmailParams(
    val customerId: String = "",
    val email: String = "",
    val subject: String? = null,
    val emailBody: String? = null,
)