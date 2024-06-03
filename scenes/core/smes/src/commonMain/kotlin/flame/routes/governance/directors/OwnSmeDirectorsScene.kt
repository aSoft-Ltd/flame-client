//@file:JsExport
//@file:Suppress("NON_EXPORTABLE_TYPE", "NOTHING_TO_INLINE")
//
//package flame.routes.governance.directors
//
//import flame.OwnSmeScheme
//import flame.SmeSceneOptions
//import flame.transformers.toPresenter
//import kase.Loading
//import kase.toLazyState
//import kollections.emptyList
//import koncurrent.Later
//import koncurrent.later.andThen
//import koncurrent.later.finally
//import koncurrent.later.then
//import koncurrent.later.zip
//import koncurrent.toLater
//import kotlinx.JsExport
//
//class OwnSmeDirectorsScene(private val options: SmeSceneOptions<OwnSmeScheme>) : SmeDirectorsScene(options) {
//
//    fun initialize(): Later<Any> {
//        directors.value = Loading("Loading directors, please wait . . .")
//        return options.api.load().zip(options.auth.session()) { (dto, session) ->
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
//            options.api.load()
//        }.zip(options.auth.session()) { (dto, session) ->
//            presenter = dto.toPresenter(options.toAttachmentOptions(session))
//            dto.admin?.directors ?: emptyList()
//        }.finally {
//            directors.value = it.toLazyState()
//        }
//    }
//}