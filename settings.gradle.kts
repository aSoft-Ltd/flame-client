import java.io.File

pluginManagement {
    includeBuild("../build-logic")
}

plugins {
    id("multimodule")
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

listOf(
    "cinematic", "keep", "lexi", "captain", "neat", "kase",
    "kash-api", "kash-client", "geo-api", "geo-client",
    "kronecker", "symphony", "epsilon-api", "krono-core", "krono-client", "hormone", "identifier-api", "identifier-client",
    "kommerce", "kollections", "koncurrent", "kommander", "cabinet-api", "pione",
    "bringer", "klip", "snitch", "krest", "sanity", "epsilon-client", "flame-core", "flame-client", "sentinel-core"
).forEach { includeBuild("../$it") }

rootProject.name = "flame-client"

includeSubs("flame-scenes", "scenes", "customers", "entity", "suppliers", "smes")
includeSubs("flame-api", "api", "customers", "entity", "suppliers", "smes")
