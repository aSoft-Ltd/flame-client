package flame

interface SmeSwotApi {
    val strengths: SmeSwotComponentApi
    val weaknesses: SmeSwotComponentApi
    val opportunities: SmeSwotComponentApi
    val threats: SmeSwotComponentApi
}