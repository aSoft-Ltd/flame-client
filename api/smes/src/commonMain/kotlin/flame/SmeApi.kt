package flame

interface SmeApi : SmeScheme {
    override val admin: SmeAdminApi
    override val funding: SmeFundingApi
    override val finance: SmeFinanceApi
    override val documents: SmeDocumentsApi
    override val governance: SmeGovernanceScheme
    override val swot: SmeSwotApi
}