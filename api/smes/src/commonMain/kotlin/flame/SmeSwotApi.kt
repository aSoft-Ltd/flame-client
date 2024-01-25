package flame

interface SmeSwotApi : SmeSwotScheme {
    override val strengths: SmeSwotComponentApi
    override val weaknesses: SmeSwotComponentApi
    override val opportunities: SmeSwotComponentApi
    override val threats: SmeSwotComponentApi
}