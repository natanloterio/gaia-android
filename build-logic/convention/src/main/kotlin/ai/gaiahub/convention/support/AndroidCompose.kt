package ai.gaiahub.convention.support

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidCompose(
    extension: ApplicationExtension,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    extension.apply {
        buildFeatures.apply {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("compose-compiler").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("compose.ui").get())
            add("implementation", libs.findLibrary("compose.ui.graphics").get())
            add("implementation", libs.findLibrary("compose.ui.tooling.preview").get())
            add("implementation", libs.findLibrary("compose.material3").get())
            add("androidTestImplementation", platform(bom))
        }
    }
}

internal fun Project.configureAndroidCompose(
    extension: LibraryExtension,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    extension.apply {
        buildFeatures.apply {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("compose-compiler").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("compose.ui").get())
            add("implementation", libs.findLibrary("compose.ui.graphics").get())
            add("implementation", libs.findLibrary("compose.ui.tooling.preview").get())
            add("implementation", libs.findLibrary("compose.material3").get())
            add("androidTestImplementation", platform(bom))
        }
    }
}
