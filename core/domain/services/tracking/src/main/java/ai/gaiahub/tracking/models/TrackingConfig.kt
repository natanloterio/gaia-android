package ai.gaiahub.tracking.models

import ai.gaiahub.tracking.providers.ProviderType

data class TrackingConfiguration(
    val enabledProviders: Set<ProviderType> = emptySet()
)