@file:Suppress("NOTHING_TO_INLINE")

package flame

class SmeSwotApiFlix(options: SmeApiFlixOptions) : SmeFlixBaseApi(options), SmeSwotApi {

    override val strengths by lazy { component(SmeKey.Swot.strengths) }

    override val weaknesses by lazy {
        logger.debug("Accessing the weakness api")
        component(SmeKey.Swot.weaknesses)
    }

    override val opportunities by lazy { component(SmeKey.Swot.opportunities) }

    override val threats by lazy { component(SmeKey.Swot.threats) }

    private inline fun component(key: SmeKey) = SmeSwotComponentApiFlix(options, key)
}