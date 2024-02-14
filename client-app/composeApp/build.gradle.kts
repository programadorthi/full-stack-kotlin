plugins {
    id("dev.programadorthi.multiplatform-application")
}

val computedPackage = "${group}.compose.app"

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