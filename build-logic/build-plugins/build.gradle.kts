import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

repositories {
    mavenLocal()
    google()
    gradlePluginPortal()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("versionCatalogGenerator") {
            id = "gaiahub.version-catalog-generator"
            implementationClass = "ai.gaiahub.version_catalog_generator.VersionCatalogGeneratorPlugin"
        }
    }
}

dependencies {
    //noinspection UseTomlInstead
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.23")
}

println("This project: ${project.name}")
println("This rootProject: ${project.rootProject.name}")