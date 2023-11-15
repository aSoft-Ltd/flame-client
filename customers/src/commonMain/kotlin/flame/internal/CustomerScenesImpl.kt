package flame.internal

import flame.CustomerScenes
import flame.CustomerScenesConfig
import flame.CustomersApiProvider
import flame.routes.CustomersScene
import flame.routes.add.CustomerAddScene
import flame.routes.dashboard.CustomersDashboardScene
import flame.routes.uid.CustomerAttachmentsScene
import flame.routes.uid.CustomerDuplicateScene
import flame.routes.uid.CustomerEditScene
import flame.routes.uid.CustomerGenerateStatementScene
import flame.routes.uid.CustomerMessagesScene
import flame.routes.uid.CustomerScene
import flame.routes.uid.CustomerSendMessageScene

@PublishedApi
internal class CustomerScenesImpl(private val config: CustomerScenesConfig<CustomersApiProvider>) : CustomerScenes {
    private val customersConfig by lazy { config.map { it.customers } }
    override val customers by lazy { CustomersScene(config) }
    override val customersDashboard by lazy { CustomersDashboardScene(customersConfig) }
    override val customer by lazy { CustomerScene(customersConfig) }
    override val customerAttachments by lazy { CustomerAttachmentsScene(customersConfig) }
    override val customerAdd by lazy { CustomerAddScene(config) }
    override val customerDuplicate by lazy { CustomerDuplicateScene(config) }
    override val customerEdit by lazy { CustomerEditScene(config) }
    override val customerGenerateStatement by lazy { CustomerGenerateStatementScene(customersConfig) }
    override val customerSendMessage by lazy { CustomerSendMessageScene(config) }
    override val customerMessages by lazy { CustomerMessagesScene(config) }
    override val customerInfo by lazy { CustomerScene(customersConfig) }
    override val customerExpenses by lazy { CustomerScene(customersConfig) }
    override val customerOverview by lazy { CustomerScene(customersConfig) }
    override val customerProjects by lazy { CustomerScene(customersConfig) }
}