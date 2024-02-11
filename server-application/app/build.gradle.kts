plugins {
    id("dev.programadorthi.ktor-module")
}

group = "${group}.app"
version = "0.0.1"

application {
    mainClass.set("$group.ApplicationKt")
}

kotlin {
    sourceSets {
        jvmMain {
            dependencies {
                implementation(libs.bundles.ktor.server)
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


