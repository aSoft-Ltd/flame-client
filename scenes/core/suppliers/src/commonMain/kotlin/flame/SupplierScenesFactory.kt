@file:Suppress("NOTHING_TO_INLINE")

package flame

import flame.internal.SupplierScenesImpl

inline fun SupplierScenes(config: SupplierScenesConfig<SuppliersApiProvider>): SupplierScenes = SupplierScenesImpl(config)