@file:JsExport

package flame

import flame.routes.collection.SmesScene
import kotlinx.JsExport

class MonSmesScenes(options: SmeSceneOption<MonSmeScheme>) {
    val collection by lazy { SmesScene(options) }
    val profile by lazy { MonSmeScenes(options) }
}