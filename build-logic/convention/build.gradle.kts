plugins {
    `kotlin-dsl`
}

group = "com.droidstarter.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.android.gradlePlugin)
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