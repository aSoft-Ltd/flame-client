package flame.utils

import flame.SmeDto
import koncurrent.Later

interface ListApi<T> {
    fun add(params: T): Later<SmeDto>
    fun update(params: T): Later<SmeDto>
    fun delete(params: T): Later<SmeDto>
}