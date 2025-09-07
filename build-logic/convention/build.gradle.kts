import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("gaiahub.version-catalog-generator") apply true
}

group = "ai.gaiahub.buildlogic"


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

sourceSets {
    main{
        kotlin{
            srcDir("build/generated/sources/versionCatalog")
        }
    }
}

dependencies {
    implementation(libs.android.tools.gradle)
    implementation(libs.kotlin.gradlePlugin)
}


gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "ai.gaiahub.convention.application.compose"
            implementationClass = "ai.gaiahub.convention.AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplication") {
            id = "ai.gaiahub.convention.application"
            implementationClass = "ai.gaiahub.convention.AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "ai.gaiahub.convention.library"
            implementationClass = "ai.gaiahub.convention.AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "ai.gaiahub.convention.library.compose"
            implementationClass = "ai.gaiahub.convention.AndroidLibraryComposeConventionPlugin"
        }

        

        register("jvmLibrary") {
            id = "ai.gaiahub.convention.jvm.library"
            implementationClass = "ai.gaiahub.convention.JvmLibraryConventionPlugin"
        }

        register("dependencies") {
            id = "ai.gaiahub.convention.dependencies"
            implementationClass = "ai.gaiahub.convention.DependenciesConventionPlugin"
        }
    }
}

println("This project: ${project.name}")
println("This rootProject: ${project.rootProject.name}")

tasks.matching { it.name == "assemble" || it.name.startsWith("assemble") }.configureEach {
    dependsOn(tasks.named("generateVersionCatalogLibs"))
}

tasks.named("compileKotlin") {
    dependsOn("generateVersionCatalog")
}