package flame

import lexi.LoggerFactory

class SmeSceneOption<out A>(
    val api: A,
    val logger: LoggerFactory
) {
    fun <R> map(transform: (A) -> R) = SmeSceneOption(transform(api), logger)
}