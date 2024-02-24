plugins {
    id("dev.programadorthi.kmp-module")
}

group = "${group}.domain"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.serialization.json)
            }
        }
    }
}
