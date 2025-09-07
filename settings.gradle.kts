@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.name = "GaiaHub"
include(":app")
include(":core")
include(":core:domain")
include(":core:domain:models")
include(":core:domain:usecases")
include(":core:domain:repositories")
include(":core:domain:services")
include(":core:domain:services:tracking")
include(":core:domain:services:observability")
include(":data")
include(":data:repositories")
include(":di")
include(":features")
include(":features:login")
include(":libs")
include(":services")
include(":services:tracking")
include(":services:observability")
include(":third-parties-sdk")