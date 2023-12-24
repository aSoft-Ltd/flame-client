package flame

import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import kollections.List
interface SmeSwotComponentApi {
    fun update(params: List<String>) : Later<SmeDto>
}