@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package flame.routes.uid

import cabinet.Attachment
import cinematic.mutableLiveOf
import epsilon.FileBlob
import epsilon.RawFile
import epsilon.RawFileInfo
import flame.EntitiesApi
import flame.EntityScenesConfig
import flame.utils.loadingCustomer
import flame.workers.UploadAttachmentWorkerParams
import hormone.UNSET
import identifier.LegalEntityDto
import identifier.utils.loadCacheableLegalEntity
import kase.LazyState
import kase.Pending
import kase.toLazyState
import koncurrent.FailedLater
import koncurrent.Later
import koncurrent.later.finally
import krest.WorkManager
import krest.params.SubmitWorkOptions
import symphony.CollectionScene
import symphony.ConfirmationBox
import symphony.paged
import kotlin.js.JsExport
import kotlin.js.JsName
import symphony.PaginationManager
import symphony.linearPaginatorOf

abstract class EntityAttachmentsScene(val config: EntityScenesConfig<EntitiesApi>) : CollectionScene<Attachment>(config) {
    val customer = mutableLiveOf<LazyState<LegalEntityDto>>(Pending)

    abstract val Type: String

    override val serializer = Attachment.serializer()

    private val workManager: WorkManager = config.workManager

    val confirm = mutableLiveOf<ConfirmationBox?>(null)

    override val paginator by lazy { linearPaginatorOf<Attachment>() }
    fun initializeWith(uid: String): Later<LegalEntityDto> {
        val res = config.loadCacheableLegalEntity(uid) {
            customer.value = loadingCustomer(uid, "attachments")
        }.finally {
            customer.value = it.toLazyState()
        }
        paginator.initialize { no, capacity ->
            config.api.rootDir(uid).list().andThen { it.paged(no, capacity) }
        }
        return res
    }

    fun deInitialize(clearPages: Boolean) {
        customer.value = Pending
        paginator.deInitialize(clearPages)
    }

    fun uploads(uid: String) = workManager.liveWorkProgress(Type, uid)

    fun refresh() = paginator.refreshAllPages()

    private val api
        get() = when (val uid = customer.value.data?.uid) {
            null -> FailedLater(NO_CUSTOMER_ID)
            else -> Later(config.api.rootDir(uid))
        }

    fun uploadAttachment(file: RawFile) {
        val info = RawFileInfo(file)
        workManager.submit(
            options = SubmitWorkOptions(
                type = Type,
                topic = customer.value.data?.uid,
                name = info.name,
                params = UploadAttachmentWorkerParams(
                    entityId = customer.value.data?.uid ?: UNSET,
                    file = file,
                    filename = info.name
                )
            )
        )
    }

    private fun uploadAttachments(files: Iterable<RawFile>) = files.forEach { uploadAttachment(it) }
    fun uploadAttachments(files: Array<RawFile>) = uploadAttachments(files.asIterable())

    @JsExport.Ignore
    fun uploadAttachments(files: List<RawFile>) = uploadAttachments(files.asIterable())

    private fun confirm(attachment: Attachment) = ConfirmationBox(
        heading = "Delete ${attachment.name}",
        details = "Are you sure you want to delete this file?"
    ) {
        onCancel { confirm.value = null }
        onConfirm {
            api.andThen {
                it.delete(attachment)
            }.andThen {
                paginator.clearPages()
                paginator.loadFirstPage()
            }.then {
                config.toaster.makeNewSuccess("Success").withBody("${attachment.name} has been deleted")
            }
        }
    }

    @JsName("deleteAttachment")
    fun delete(attachment: Attachment) {
        confirm.value = confirm(attachment)
    }

    private companion object {
        private val NO_CUSTOMER_ID = IllegalStateException("Customer Id has not been set")
    }
}