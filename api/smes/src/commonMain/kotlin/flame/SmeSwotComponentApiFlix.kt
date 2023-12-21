package flame

import kollections.List
import kollections.ListSerializer
import koncurrent.Later
import kotlinx.serialization.builtins.serializer

class SmeSwotComponentApiFlix(options: SmeApiFlixOptions, private val key: SmeKey) : SmeFlixBaseApi(options), SmeSwotComponentApi {
    override fun update(params: List<String>): Later<SmeDto> = update(key, options.codec.encodeToString(ListSerializer(String.serializer()), params))
}