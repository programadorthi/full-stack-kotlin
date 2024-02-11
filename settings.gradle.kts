val builds = listOf(
    "build-logic",
    "domain-model",
    "platforms",
)

rootProject.name = "full-stack-kotlin"

// Issue: https://github.com/gradle/gradle/issues/2534
val gradleProperties = "gradle.properties"
builds.forEach { projectName ->
    includeBuild(projectName)

    val includeGradleProperties = File(rootDir, "$projectName/$gradleProperties")
    if (!includeGradleProperties.exists()) {
        file(gradleProperties).copyTo(includeGradleProperties)
    }
}
