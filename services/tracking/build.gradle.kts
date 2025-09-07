plugins {
    id("ai.gaiahub.convention.library")
    id("ai.gaiahub.convention.dependencies")
}

android {
    namespace = "ai.gaiahub.services.tracking"
}
dependencies {
    implementation(project(":core:domain:services:tracking"))

    implementation(project(":third-parties-sdk"))
}