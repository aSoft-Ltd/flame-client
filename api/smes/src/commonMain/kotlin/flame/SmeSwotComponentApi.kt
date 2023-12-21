package flame

import koncurrent.Later
import kollections.List
interface SmeSwotComponentApi {
    fun update(params: List<String>) : Later<SmeDto>
}