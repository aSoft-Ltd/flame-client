package flame

interface SmeApi : SmeScheme {
    override val documents: SmeDocumentsApi
}