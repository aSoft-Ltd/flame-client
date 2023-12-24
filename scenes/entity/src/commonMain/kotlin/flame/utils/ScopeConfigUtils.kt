package flame.utils

import identifier.IdentifierScenesConfig
import identifier.LegalEntityApi
import identifier.utils.loadSelectedCustomer
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch

fun IdentifierScenesConfig<LegalEntityApi>.loadCacheableCustomer(
    uid: String,
    beforeNetwork: () -> Unit
) = cache.loadSelectedCustomer().andThen {
    if (it?.uid == uid) return@andThen Later(it)
    beforeNetwork()
    api.load(uid)
}