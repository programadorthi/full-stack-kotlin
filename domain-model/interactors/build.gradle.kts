plugins {
    id("dev.programadorthi.kmp-module")
}

group = "${group}.domain"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("dev.programadorthi.full.stack.domain:models:$version")
                implementation(libs.programadorthi.state.validators)
            }
        }
    }
}
