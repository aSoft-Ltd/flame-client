@file:Suppress("NOTHING_TO_INLINE")

package flame.utils

import kase.bagOf
import sanity.Event
import sanity.EventBus
import sanity.Subscriber

internal inline val EventBus.subscriberBag get() = bagOf<Subscriber>()

const val TOPIC_INVALIDATE_CACHE_FLAME_CUSTOMER = "flame/customer/cache/invalidate"

inline fun EventBus.subscribeInvalidateCache(
    noinline callback: (Event) -> Unit
) = subscribe(TOPIC_INVALIDATE_CACHE_FLAME_CUSTOMER, callback)

inline fun EventBus.dispatchInvalidateCache() = dispatch(TOPIC_INVALIDATE_CACHE_FLAME_CUSTOMER)