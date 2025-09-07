plugins {
    id("ai.gaiahub.convention.library")
    id("ai.gaiahub.convention.dependencies")
}

android {
    namespace = "ai.gaiahub.bara.code.domain.usecases"
}

dependencies {
    implementation(project(":core:domain:services:tracking"))
}