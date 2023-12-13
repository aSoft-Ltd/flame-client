package flame.routes.ratios.pnl.output

class SmeAnnualTax(var income: Double? = null) {
    val total get() = income ?: 0.0
}