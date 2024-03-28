@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import kotlinx.JsExport

sealed interface SmeDocumentsScenes {
    val list: SmeAbstractDocumentScene
    val financials: SmeAbstractDocumentScene
    val additional: SmeAbstractDocumentScene
}