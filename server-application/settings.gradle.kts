dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "server-application"

includeBuild("../domain-model")
includeBuild("../platforms")

include("app")