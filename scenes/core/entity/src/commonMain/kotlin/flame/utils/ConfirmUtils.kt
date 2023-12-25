package flame.utils

import identifier.LegalEntityPresenter
import kollections.List
import kollections.size
import symphony.ConfirmActionsBuilder
import symphony.ConfirmationBox

internal fun confirmDelete(
    customer: LegalEntityPresenter,
    builder: ConfirmActionsBuilder.() -> Unit
) = ConfirmationBox(
    heading = "Delete Customer",
    details = "Are you sure you want to delete ${customer.name}?",
    message = "Deleting ${customer.name}",
    actionsBuilder = builder
)

internal fun confirmDelete(
    customers: List<LegalEntityPresenter>,
    builder: ConfirmActionsBuilder.() -> Unit
) = ConfirmationBox(
    heading = "Delete Customers",
    details = "Are you sure you want to delete ${customers.size} customers?",
    message = "Deleting ${customers.size} customers",
    actionsBuilder = builder
)