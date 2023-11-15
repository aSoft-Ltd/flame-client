package flame.utils

import flame.CustomersApi
import identifier.IdentifierScenesConfig
import identifier.utils.loadSelectedCustomer
import koncurrent.Later

fun IdentifierScenesConfig<CustomersApi>.loadCacheableCustomer(
    uid: String,
    beforeNetwork: () -> Unit
) = cache.loadSelectedCustomer().andThen {
    if (it?.uid == uid) return@andThen Later(it)
    beforeNetwork()
    api.load(uid)
}