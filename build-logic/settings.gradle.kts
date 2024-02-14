dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google()
    }

    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

includeBuild("../platforms")

rootProject.name = "build-logic"

include("commons")
include("compose-module")
include("kmp-module")
include("ktor-module")
include("multiplatform-module")
include("serialization-module")