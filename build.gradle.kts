defaultTasks("run")

tasks.register("run") {
    dependsOn(gradle.includedBuild("server-application").task(":app:jvmRun"))
}
