package flame.internal

import flame.SupplierScenes
import flame.SupplierScenesConfig
import flame.SuppliersApiProvider
import flame.routes.SuppliersScene
import flame.routes.add.SupplierAddScene
import flame.routes.uid.SupplierAttachmentsScene
import flame.routes.uid.SupplierDuplicateScene
import flame.routes.uid.SupplierEditScene
import flame.routes.uid.SupplierGenerateStatementScene
import flame.routes.uid.SupplierMessagesScene
import flame.routes.uid.SupplierScene
import flame.routes.uid.SupplierSendMessageScene

@PublishedApi
internal class SupplierScenesImpl(private val config: SupplierScenesConfig<SuppliersApiProvider>) : SupplierScenes {
    private val suppliersConfig by lazy { config.map { it.suppliers } }
    override val suppliers by lazy { SuppliersScene(config) }
    override val supplier by lazy { SupplierScene(suppliersConfig) }
    override val supplierAttachments by lazy { SupplierAttachmentsScene(suppliersConfig) }
    override val supplierAdd by lazy { SupplierAddScene(config) }
    override val supplierDuplicate by lazy { SupplierDuplicateScene(config) }
    override val supplierEdit by lazy { SupplierEditScene(config) }
    override val supplierGenerateStatement by lazy { SupplierGenerateStatementScene(suppliersConfig) }
    override val supplierSendMessage by lazy { SupplierSendMessageScene(config) }
    override val supplierMessages by lazy { SupplierMessagesScene(config) }
    override val supplierInfo by lazy { SupplierScene(suppliersConfig) }
    override val supplierOverview by lazy { SupplierScene(suppliersConfig) }
}