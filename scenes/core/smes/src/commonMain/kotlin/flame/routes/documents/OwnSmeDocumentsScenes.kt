@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.documents

import flame.OwnSmeApi
import flame.SmeSceneOptions
import flame.tasks.OwnSmeUploadDocumentTask
import kotlinx.JsExport
import krest.register

class OwnSmeDocumentsScenes(options: SmeSceneOptions<OwnSmeApi>) : SmeDocumentsScenes {
    init {
        options.tasks.register { OwnSmeUploadDocumentTask(options) }
    }

    override val list by lazy { OwnSmeDocumentListScene(options) }
    override val financials by lazy { OwnSmeFinancialRecordsScene(options) }
    override val additional by lazy { OwnSmeAdditionalDocumentsScene(options) }
}