package flame

import kollections.List
import kollections.ListSerializer
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import kotlinx.serialization.builtins.serializer

class SmeSwotComponentApiFlix(options: SmeApiFlixOptions, private val key: SmeKey) : SmeFlixBaseApi(options), SmeSwotComponentApi {
    override fun update(params: List<String>): Later<SmeDto> = update(key, options.codec.encodeToString(ListSerializer(String.serializer()), params))
}