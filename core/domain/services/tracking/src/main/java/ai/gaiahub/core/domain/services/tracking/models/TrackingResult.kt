package ai.gaiahub.core.domain.services.tracking.models

import ai.gaiahub.core.domain.services.tracking.providers.ProviderType

sealed class TrackingResult {
    object Success : TrackingResult()
    data class Error(val exception: Throwable, val provider: ProviderType) : TrackingResult()
    data class PartialSuccess(
        val successfulProviders: List<ProviderType>,
        val failedProviders: Map<ProviderType, Throwable>
    ) : TrackingResult()
}