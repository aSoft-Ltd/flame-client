package flame.params

import kotlinx.serialization.Serializable

@Serializable
data class SendSMSParams(
    val customerId: String,
    val mobile: String?,
    val message: String?,
)