package ai.gaiahub.version_catalog_generator

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class GenerateVersionCatalogTask : DefaultTask() {
    @get:InputFile
    abstract val tomlFile: RegularFileProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun generate() {
        val toml = tomlFile.get().asFile
        val outDir = outputDir.get().asFile
        val outputFile = File(outDir, "Libs.kt")

        if (outputFile.exists()) {
            outputFile.delete()
        }

        if (!toml.exists()) {
            throw IllegalArgumentException("TOML file not found: ${toml.path}")
        }

        val tomlContent = toml.readText()
        val kotlinContent = LocalVersionCatalogGenerator.generate(tomlContent)

        outDir.mkdirs()
        outputFile.writeText(kotlinContent)
        println("Generated version catalog at: ${outputFile.path}")
    }
}

