package flame.utils

import kase.Loading

inline fun loadingCustomer(
    uid: String?,
    context: String
) = Loading<Nothing>("Loading $context for customer (uid=$uid), please wait . . .")