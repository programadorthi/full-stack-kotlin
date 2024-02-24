defaultTasks("run")

tasks.register("run") {
    dependsOn(gradle.includedBuild("server-application").task(":app:jvmRun"))
    // ANDROID
    //dependsOn(gradle.includedBuild("client-app").task(":composeApp:installDebug"))
    // WEB
    //dependsOn(gradle.includedBuild("client-app").task(":composeApp:jsBrowserDevelopmentRun"))
    // DESKTOP
    dependsOn(gradle.includedBuild("client-app").task(":composeApp:run"))
}
