plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

kotlin {
    jvm { library() }
    if (Targeting.JS) js(IR) { library() }
//    if (Targeting.WASM) wasm { library() }
    if (Targeting.OSX) osxTargets() else listOf()
//    if (Targeting.NDK) ndkTargets() else listOf()
    if (Targeting.LINUX) linuxTargets() else listOf()
    if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.flameApiSuppliers)
                api(libs.identifier.legal.sdk.client.core)
                api(libs.captain.navigator.api)
                api(libs.symphony.collections)
                api(libs.symphony.input.dialog)
                api(libs.cabinet.api.core)
                api(libs.epsilon.api.file) // Because we want to upload customer documents as attachments
                api(libs.krest.core) // Because we want to write background workers for uploading attachments
			    api(libs.sanity.core)
                api(libs.snitch.api)
                api(libs.klip.api) // Because we need to copy urls to the clipboard
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
