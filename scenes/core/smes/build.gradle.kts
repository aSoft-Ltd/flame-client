plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

kotlin {
    jvm { library() }
    if (Targeting.JS) js(IR) { library() }
//    if (Targeting.WASM) wasm { library() }
//    if (Targeting.OSX) osxTargets() else listOf()
//    if (Targeting.NDK) ndkTargets() else listOf()
//    if (Targeting.LINUX) linuxTargets() else listOf()
//    if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.flameApiSmes)
                api(libs.sentinel.api.enterprise.authentication.core)?.because("We need to get access to user session for authenticating document downloads")
                api(libs.krono.fields)
                api(libs.identifier.fields)
                api(libs.geo.fields)
                api(libs.captain.navigator.api)
                api(libs.symphony.collections)
                api(libs.symphony.input.dialog)
                api(libs.symphony.input.sheet)
                api(libs.cabinet.api.core)
//                api(libs.epsilon.core)?.because("We want to upload customer documents as attachments")
                api(libs.epsilon.fields)?.because("We want to upload customer documents as attachments")
                api(libs.cabinet.presenters)?.because("We need to present the uploaded attachments")
                api(libs.krest.core)?.because("We want to write background workers for uploading attachments")
                api(libs.sanity.core)
                api(libs.snitch.api)
                api(libs.klip.api)?.because("We need to copy urls to the clipboard")
                api(libs.bringer.api)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.cinematic.live.test)
                implementation(libs.kommander.coroutines)
            }
        }
    }
}
