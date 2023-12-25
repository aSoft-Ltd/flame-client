package flame

import flame.internal.CustomerScenesImpl

inline fun CustomerScenes(config: CustomerScenesConfig<CustomersApiProvider>): CustomerScenes = CustomerScenesImpl(config)