Refactor my Kotlin-based Gradle project to use the build-logic convention plugins approach.

Create a build-logic module (or buildSrc, if preferred) using the convention plugins pattern.

Extract repeated or shared logic from build.gradle.kts files (e.g. common dependencies, Kotlin/JVM configuration, Android config, etc.) into custom convention plugins.

Replace this logic in the individual project modules (build.gradle.kts) by applying the new plugins via plugins { id("my.plugin.id") }.

Ensure the plugins are registered correctly in build-logic/build.gradle.kts using pluginBundle or the appropriate Gradle Plugin Development DSL.

Use Kotlin DSL (build.gradle.kts) for all files.

Update settings.gradle.kts to include the build-logic module as a buildSrc replacement if needed.

Keep the project compiling and running tests after the refactor.

Bonus: group plugins logically, e.g., convention.kotlin-jvm, convention.android-library, convention.testing.