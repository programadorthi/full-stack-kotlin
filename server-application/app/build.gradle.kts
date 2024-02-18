plugins {
    id("dev.programadorthi.ktor-module")
}

group = "${group}.app"

kotlin {
    jvm {
        mainRun {
            mainClass.set("$group.ApplicationKt")
        }
    }

    sourceSets {
        jvmMain {
            dependencies {
                implementation("dev.programadorthi.full.stack.domain:models:$version")
                implementation("dev.programadorthi.full.stack.domain:interactors:$version")
                implementation(libs.programadorthi.state.core)
                implementation(compose.runtime)
                implementation(libs.bundles.exposed)
                implementation(libs.bundles.ktor.server)
                implementation(libs.h2)
                implementation(libs.logback.classic)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.test.unit.ktor.server)
            }
        }
    }
}


