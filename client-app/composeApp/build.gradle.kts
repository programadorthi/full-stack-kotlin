plugins {
    id("dev.programadorthi.multiplatform-application")
}

val computedPackage = "${group}.compose.app"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("dev.programadorthi.full.stack.domain:interactors:$version")
                implementation("dev.programadorthi.full.stack.domain:models:$version")
                implementation(libs.programadorthi.state.compose)
            }
        }
    }
}

android {
    namespace = computedPackage

    defaultConfig {
        applicationId = computedPackage
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            packageName = computedPackage
        }
    }
}

compose.experimental {
    web.application {}
}