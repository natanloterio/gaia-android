package ai.gaiahub.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class DependenciesConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val libs = target.extensions.getByType<VersionCatalogsExtension>().named("libs")
        target.dependencies {
            add("implementation", libs.findLibrary("material").get())
            add("implementation", platform(libs.findLibrary("compose.bom").get()))
            add("implementation", libs.findLibrary("compose.ui").get())
            add("implementation", libs.findLibrary("compose.ui.graphics").get())
            add("implementation", libs.findLibrary("compose.ui.tooling.preview").get())
            add("implementation", libs.findLibrary("compose.material3").get())
            add("testImplementation", libs.findLibrary("junit").get())

            add("implementation", platform(libs.findLibrary("koin.bom").get()))
            add("implementation", libs.findLibrary("koin.core").get())
            add("implementation", libs.findLibrary("koin.android").get())
            add("implementation", libs.findLibrary("koin.androidx.compose").get())
            add("testImplementation", libs.findLibrary("koin.test").get())
            add("testImplementation", libs.findLibrary("koin.test.junit4").get())
        }
    }
}
