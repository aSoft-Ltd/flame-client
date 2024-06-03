//@file:JsExport
//@file:Suppress("NON_EXPORTABLE_TYPE", "NOTHING_TO_INLINE")
//
//package flame.routes.governance.directors
//
//import flame.MonSmeScheme
//import flame.SmeSceneOptions
//import flame.transformers.toPresenter
//import kase.Loading
//import kase.toLazyState
//import kollections.emptyList
//import koncurrent.later.andThen
//import koncurrent.later.finally
//import koncurrent.later.then
//import koncurrent.later.zip
//import koncurrent.toLater
//import kotlinx.JsExport
//
//class MonSmeDirectorsScene(private val options: SmeSceneOptions<MonSmeScheme>) : SmeDirectorsScene(options) {
//
//    fun initialize(uid: String) {
//        directors.value = Loading("Loading directors, please wait . . .")
//        options.api.load(uid).zip(options.auth.session()) { (dto, session) ->
//            presenter = dto.toPresenter(options.toAttachmentOptions(session))
//            dto.admin?.directors ?: emptyList()
//        }.finally {
//            directors.value = it.toLazyState()
//        }
//    }
//
//    override fun refresh() {
//        directors.value = Loading("Updating directors list, please wait . . .")
//        presenter.toLater().then {
//            it ?: throw MissingPresenterException()
//        }.andThen {
//            options.api.load(it.uid)
//        }.zip(options.auth.session()) { (dto, session) ->
//            presenter = dto.toPresenter(options.toAttachmentOptions(session))
//            dto.admin?.directors ?: emptyList()
//        }.finally {
//            directors.value = it.toLazyState()
//        }
//    }
//}