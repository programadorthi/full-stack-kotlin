val builds = listOf(
    "platforms",
    "build-logic",
    "client-app",
    "domain-model",
    "server-application",
)

rootProject.name = "full-stack-kotlin"

// Issue: https://github.com/gradle/gradle/issues/2534
val gradleProperties = "gradle.properties"
builds.forEach { projectName ->
    includeBuild(projectName)

    val includeGradleProperties = File(rootDir, "$projectName/$gradleProperties")
    file(gradleProperties).copyTo(target = includeGradleProperties, overwrite = true)
}
