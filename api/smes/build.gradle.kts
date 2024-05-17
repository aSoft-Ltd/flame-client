plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.flame.schemes.smes)
                api(libs.geo.coordinates)
                api(ktor.client.core)
                api(libs.lexi.api)
                api(libs.keep.api)
                api(libs.koncurrent.later.coroutines)
                api(libs.sentinel.schemes.enterprise.authentication.core)?.because("We need access to UserSession")
                api(libs.status.core)
                api(libs.kase.response.ktor.client)
                api(libs.krono.api)
                api(libs.kronecker.core)
                api(libs.cabinet.api.core)
                api(libs.identifier.legal.dtos)
            }
        }

        val commonTest by getting {
            dependencies {
                api(libs.kommander.coroutines)
            }
        }
    }
}