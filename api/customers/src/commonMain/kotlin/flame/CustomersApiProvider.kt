package flame

interface CustomersApiProvider : EntitiesApiProvider {
    val customers: CustomersApi
}