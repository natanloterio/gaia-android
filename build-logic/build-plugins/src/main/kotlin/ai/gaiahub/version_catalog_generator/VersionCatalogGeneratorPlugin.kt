package ai.gaiahub.version_catalog_generator

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

@Suppress("unused")
class VersionCatalogGeneratorPlugin : Plugin<Project?> {
    override fun apply(project: Project) {

        println("Applying VersionCatalogGeneratorPlugin to project: ${project.name}")

        val generateTask = project.tasks.register<GenerateVersionCatalogTask>("generateVersionCatalog") {
            tomlFile.set(project.rootProject.file("../gradle/libs.versions.toml"))
            outputDir.set(project.layout.buildDirectory.dir("generated/sources/versionCatalog"))
        }

        // Make all assemble tasks depend on generateVersionCatalog
        project.tasks.configureEach {
            if (name.contains("assemble", ignoreCase = true)) {
                dependsOn(generateTask)
            }
        }
    }
}
