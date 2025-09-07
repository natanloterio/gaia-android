package ai.gaiahub.core.domain.services.tracking.providers

import ai.gaiahub.core.domain.services.tracking.events.TrackingEvent

interface TrackingProvider {
    val type: ProviderType
    suspend fun trackEvent(event: TrackingEvent): Result<Unit>
}