package flame

import koncurrent.Later

interface SmeSwotComponentApi {
    fun update(params: List<String>) : Later<SmeDto>
}