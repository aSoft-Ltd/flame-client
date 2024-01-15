package flame

import kollections.List
import koncurrent.Later
import kronecker.LoadOptions

interface BusinessesApi {
    fun load(options: LoadOptions = LoadOptions()): Later<List<SmeDto>>
}