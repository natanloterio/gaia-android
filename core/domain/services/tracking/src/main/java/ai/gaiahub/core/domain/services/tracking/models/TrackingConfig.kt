package ai.gaiahub.core.domain.services.tracking.models

import ai.gaiahub.core.domain.services.tracking.providers.ProviderType

data class TrackingConfiguration(
    val enabledProviders: Set<ProviderType> = emptySet()
)