package ai.gaiahub.version_catalog_generator

import org.gradle.api.provider.ProviderFactory
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import javax.inject.Inject

abstract class VersionCatalogGeneratorCacheService: BuildService<BuildServiceParameters.None?> {
    private val cache = mutableMapOf<String, String>()

}