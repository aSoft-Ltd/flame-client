package flame

import hormone.Creator
import hormone.Deleter
import hormone.Updater
import hormone.Loader
import hormone.Identified
import cabinet.Directory
import identifier.Comm
import identifier.ContactDto
import identifier.CorporateDto
import identifier.params.ContactParams
import kollections.List
import kollections.Map
import koncurrent.Later
import kronecker.LoadOptions

interface ContactsApi :
    Creator<ContactParams, ContactDto>,
    Updater<ContactParams, ContactDto>,
    Loader<ContactDto>,
    Deleter<ContactDto>,
    Directory {

    /**
     * params.uid -> should be contact uid that you are adding comms
     */
    fun add(params: Identified<String, Comm>): Later<ContactDto>

    /**
     * params.uid -> should be contact uid that you are removing comms
     */
    fun remove(params: Identified<String, Comm>): Later<ContactDto>

    fun loadAllInCorporate(params: String): Later<List<ContactDto>>

    fun loadAll(option: LoadOptions? = null): Later<Map<ContactDto, CorporateDto>>
}