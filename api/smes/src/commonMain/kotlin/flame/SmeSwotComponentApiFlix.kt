package flame

import koncurrent.Later
import kotlinx.serialization.encodeToString

class SmeSwotComponentApiFlix(options: SmeApiFlixOptions, private val key: SmeKey) : SmeFlixBaseApi(options), SmeSwotComponentApi {
    override fun update(params: List<String>): Later<SmeDto> = update(key, options.codec.encodeToString(params))
}